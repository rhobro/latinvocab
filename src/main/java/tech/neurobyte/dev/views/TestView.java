/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: TestView.java
 * Last Modified: 24/04/2021, 21:10
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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.*;
import com.wontlost.sweetalert2.SweetAlert2Vaadin;
import tech.neurobyte.dev.data.Filter;
import tech.neurobyte.dev.data.Word;
import tech.neurobyte.dev.views.misc.Alert;
import tech.neurobyte.dev.views.testers.MultipleChoice;
import tech.neurobyte.dev.views.testers.Tester;
import tech.neurobyte.dev.views.testers.TypeIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route("test")
@Tag("test-view")
@JsModule("./views/test-view.ts")
public class TestView extends LitTemplate implements HasUrlParameter<String> {

    // components
    @Id("header")
    private VerticalLayout header;
    @Id("word")
    private H1 word;
    @Id("gramType")
    private H3 gramType;
    @Id("tester")
    private VerticalLayout tester;
    @Id("score")
    private Label score;
    @Id("prevQ")
    private Button nextQ;

    // params
    private boolean latin = true;
    private int nQs = -1;
    private boolean invalidURL = false;

    private List<Word> words;
    private int i = 0;
    private int scoreInt;

    // timers
    private final SimpleTimer total = new SimpleTimer();
    private final SimpleTimer tpq = new SimpleTimer();

    public TestView() {
        // else cont
        nextQ.addClickListener(e -> next());

        // setup timers
        for (var t : Arrays.asList(total, tpq)) {
            t.setHours(true);
            t.setFractions(true);
        }

        // callbacks
        total.addTimerEndEvent(e -> finish("Oh no! You ran out of time."));
        tpq.addTimerEndEvent(e -> {
            Notification.show("You ran out of time for that question. Moving on.");
            next();
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
            return;
        }

        // reset question timer
        total.start();
    }

    private void next() {
        // if finished
        if (i == nQs) {
            finish(String.format("Well done! You got %s. Do you want to do the quiz again?", score.getText()));
            return;
        }

        var w = words.get(i);
        // update display of words
        word.setText(latin ? w.qLa : w.qEn);
        gramType.setText(w.getType());
        // update tester
        t().nextWord(w);
        // reset question timer
        tpq.reset();
        if (!tpq.isRunning()) {
            tpq.start();
        }

        i++;
    }

    private void finish(String msg) {
        // popup
        var cfg = Alert.yesNo("Finished", msg, "Soldier on", "Take me back");
        var popup = new SweetAlert2Vaadin(cfg);
        popup.addConfirmListener(e -> {
            // redo quiz
            this.getUI().ifPresent(ui -> ui.getPage().reload());
        });
        popup.addCancelListener(e -> {
            // go back to home
            this.getUI().ifPresent(ui -> ui.navigate(""));
        });
        popup.open();
    }

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
        if (params.containsKey("n")) {
            var n = Integer.parseInt(params.get("n").get(0));
            // set max as manual amount rather than size of list
            if (n > 0) {
                nQs = n;
            }
        } else {
            nQs = words.size();
        }
        // timers
        if (params.containsKey("t")) {
            total.setStartTime(Double.parseDouble(params.get("t").get(0)) * 60);
            header.add(total);
        }
        if (params.containsKey("tpq")) {
            tpq.setStartTime(Integer.parseInt(params.get("tpq").get(0)));
            header.add(tpq);
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
            case "type" -> Filter.byType(params.get("sel"));
            default -> invalidURL = true;
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
        // set lang
        t().setLang(latin);
        t().setOnCorrect(() -> {
            scoreInt++; // update score
        });
        t().setOnAnswer(() -> {
            score.setText(String.format("%d / %d", scoreInt, nQs)); // update score
            tpq.reset(); // reset each q timer
            next(); // move immediately to next question
        });


        init();
        next();
    }

    private Tester t() {
        return (Tester) tester.getChildren().toArray()[0];
    }
}
