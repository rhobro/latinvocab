/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: TestView.java
 * Last Modified: 01/05/2021, 13:32
 */

package tech.neurobyte.dev.views;

import com.flowingcode.vaadin.addons.simpletimer.SimpleTimer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.*;
import com.wontlost.sweetalert2.SweetAlert2Vaadin;
import tech.neurobyte.dev.data.Filter;
import tech.neurobyte.dev.data.Word;
import tech.neurobyte.dev.views.components.Alert;
import tech.neurobyte.dev.views.testers.MultipleChoice;
import tech.neurobyte.dev.views.testers.Tester;
import tech.neurobyte.dev.views.testers.TypeIn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route("test")
@Tag("test-view")
@JsModule("./views/test-view.ts")
public class TestView extends LitTemplate implements HasUrlParameter<String> {

    // components
    @Id("header")
    private HorizontalLayout header;
    @Id("word")
    private H1 word;
    @Id("gramType")
    private H3 gramType;
    @Id("tester")
    private VerticalLayout tester;
    @Id("score")
    private Label score;
    @Id("nextQ")
    private Button nextQ;

    @Id("pause")
    private Button pause;
    @Id("pauseIcon")
    private Element pauseIcon;
    @Id("total")
    private SimpleTimer total;
    @Id("tpq")
    private SimpleTimer tpq;
    // params
    private boolean latin = true;

    public TestView() {
        // else cont
        nextQ.addClickListener(e -> next());

        // timer callbacks
        total.addTimerEndEvent(e -> finish("Oh no! You ran out of time."));
        tpq.addTimerEndEvent(e -> {
            Notification.show("You ran out of time for that question. Moving on.");
            next(); // go to next question
        });
        total.pause();
        tpq.pause();
        total.setVisible(false);
        tpq.setVisible(false);

        // pause button
        pause.addClickListener(e -> {
            if (pauseIcon.getProperty("icon").equals("vaadin:play")) {
                // already paused

                // resume time
                total.start();
                tpq.start();
                // resume tester
                t().setEnabled(true);
                // normalise button colour
                pauseIcon.setProperty("icon", "vaadin:pause");

            } else {
                // not paused

                // pause time
                total.pause();
                tpq.pause();
                // pause tester
                t().setEnabled(false);
                // success button colour
                pauseIcon.setProperty("icon", "vaadin:play");
            }
        });
    }

    private void init() {
        // if invalid data, return to home
        if (invalidURL) {
            // popup
            var alCfg = Alert.errorCancel(
                    "Oops...",
                    "The site just fucked up. Sorry you had to witness that.",
                    "Take me back");
            var alert = new SweetAlert2Vaadin(alCfg);
            alert.addConfirmListener(e -> e.getSource().getUI().ifPresent(ui -> ui.navigate("")));
            alert.open();
        }
    }

    private void next() {
        // if finished
        if (i == nQs) {
            finish(String.format("Well done! You got %s. Do you want to do the quiz again?",
                    String.format("%d / %d", scoreInt, nQs)));
            return;
        }

        var w = words.get(i);
        // update display of words
        word.setText(latin ? w.qLa : w.qEn);
        gramType.setText(w.getType());
        // update tester
        t().nextWord(w);
        // reset timers
        if (!total.isRunning() && tN != -1) {
            total.start();
        }
        tpq.reset();
        if (!tpq.isRunning() && tpqN != -1) {
            tpq.start();
        }
        // enable pausing
        pause.setEnabled(true);

        i++;
    }

    private void finish(String msg) {
        t().setEnabled(false);

        // popup
        var cfg = Alert.yesNo("Finished", msg, "Soldier on", "Take me back");
        var popup = new SweetAlert2Vaadin(cfg);
        popup.addConfirmListener(e -> this.getUI().ifPresent(ui -> ui.getPage().reload())); // reload
        popup.addCancelListener(e -> this.getUI().ifPresent(ui -> ui.navigate(""))); // go back to home
        popup.open();
    }

    private int nQs = -1;
    private boolean invalidURL = false;
    private List<Word> words;
    private int i = 0;
    private int scoreInt;
    private double tN = -1;
    private int tpqN = -1;

    @Override
    public void setParameter(BeforeEvent e, @OptionalParameter String s) {
        var loc = e.getLocation();
        QueryParameters queryParams = loc.getQueryParameters();
        var params = queryParams.getParameters();

        // check for necessary values
        if (!params.containsKey("latin") ||
                !params.containsKey("sel") ||
                !params.containsKey("filter") ||
                !params.containsKey("type")) {
            invalidURL = true;
            return;
        }

        latin = Boolean.parseBoolean(params.get("latin").get(0));

        // timers
        if (params.containsKey("t")) {
            tN = Double.parseDouble(params.get("t").get(0)) * 60;

            total.setVisible(true);
            total.setStartTime(tN);
            total.start();
        }
        if (params.containsKey("tpq")) {
            tpqN = Integer.parseInt(params.get("tpq").get(0));

            tpq.setVisible(true);
            tpq.setStartTime(tpqN);
            total.start();
        }

        // init word list
        switch (params.get("filter").get(0)) {
            case "all" -> words = Filter.all();
            case "stage" -> {
                var stages = new ArrayList<Integer>();
                for (var stageStr : params.get("sel")) {
                    stages.add(Integer.parseInt(stageStr));
                }
                words = Filter.byStage(stages);
            }
            case "letter" -> words = Filter.byLetter(
                    Boolean.parseBoolean(params.get("latin").get(0)),
                    String.join("", params.get("sel")));
            case "type" -> words = Filter.byType(params.get("sel"));
            default -> {
                invalidURL = true;
                words = Collections.emptyList();
            }
        }

        // number of questions
        nQs = words.size();
        if (params.containsKey("n")) {
            var n = Integer.parseInt(params.get("n").get(0));
            // set max as manual amount rather than size of list
            if (n > 0 && n < nQs) {
                nQs = n;
            }
        }

        // init score
        score.setText(String.format("%d / %d", scoreInt, nQs));

        // init tester
        tester.removeAll();
        if (params.get("type").get(0).equals("mcq")) {
            tester.add(new MultipleChoice());
        } else {
            tester.add(new TypeIn());
        }

        // custom callbacks
        // callbacks
        t().setOnCorrect(() -> {
            scoreInt++; // update score
            Notification.show("Correct!"); // notify to show after quick change
            next(); // move swiftly on
        });
        t().setOnAnswer(() -> {
            // pause timers while candidate reflects on mistake
            total.pause();
            tpq.pause();
            // disable pausing
            pause.setEnabled(false);

            score.setText(String.format("%d / %d", scoreInt, nQs)); // update score
        });

        // set lang
        t().setLang(latin);

        init();
        next();
    }

    private Tester t() {
        return (Tester) tester.getChildren().toArray()[0];
    }
}
