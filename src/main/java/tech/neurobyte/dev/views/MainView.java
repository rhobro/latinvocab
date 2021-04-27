/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MainView.java
 * Last Modified: 27/04/2021, 21:18
 */

package tech.neurobyte.dev.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.vaadin.tabs.PagedTabs;
import tech.neurobyte.dev.data.Word;
import tech.neurobyte.dev.views.customizers.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PWA(name = "Latin Vocab", shortName = "Latin")
@Route("")
@Tag("main-view")
@JsModule("./views/main-view.ts")
public class MainView extends LitTemplate {

    public static MainView main;

    // tester params
    @Id("customizer")
    private VerticalLayout customizer;

    @Id("testDirection")
    private Button testDirection;
    @Id("testDirectionIcon")
    private Element testDirectionIcon;
    // components
    @Id("body")
    private VerticalLayout body;
    @Id("type")
    private Select<String> type;

    @Id("nQs")
    private IntegerField nQs;
    @Id("unlimQs")
    private Button unlimQs;
    @Id("unlimQsIcon")
    private Element unlimQsIcon;

    @Id("time")
    private NumberField time;
    @Id("unlimT")
    private Button unlimT;
    @Id("unlimTIcon")
    private Element unlimTIcon;

    @Id("timePQ")
    private IntegerField timePQ;
    @Id("unlimTPQ")
    private Button unlimTPQ;
    @Id("unlimTPQIcon")
    private Element unlimTPQIcon;

    @Id("go")
    private Button go;

    @Id("wordCount")
    private Label wordCount;

    // internal components
    private final VerticalLayout container = new VerticalLayout();
    private final PagedTabs tabs = new PagedTabs(container);
    private final Grid<Word> wordGrid = new Grid<>(Word.class);

    public MainView() {
        main = this;

        // setup tabs
        tabs.add("All", new All(), false);
        tabs.add("By Stage", new ByStage(), false);
        tabs.add("By Letter", new ByLetter(), false);
        tabs.add("By Type", new ByType(), false);
        // equally space tabs
        tabs.getChildren().findFirst().ifPresent(c -> c.getChildren().forEach(e -> ((Tab) e).setFlexGrow(1)));
        // refresh on tab switch
        tabs.addSelectedChangeListener(e -> refresh());
        // add to layout
        container.setAlignItems(FlexComponent.Alignment.CENTER);
        customizer.add(tabs, container);

        // setup params
        // test direction
        testDirection.addClickListener(e -> {
            testDirectionIcon.setProperty("icon", isLatin() ? "vaadin:arrow-left" : "vaadin:arrow-right");
            refresh();
        });

        // type
        type.setItems("Multiple Choice", "Type-in");

        // disables
        var disableToF = Map.of(
                unlimQs, nQs,
                unlimT, time,
                unlimTPQ, timePQ
        );
        for (var b : disableToF.keySet()) {
            b.addClickListener(e -> {
                var field = disableToF.get(e.getSource());
                field.setEnabled(!field.getElement().isEnabled());
            });
            b.click();
        }

        // setup go button
        go.addClickListener(e -> start());

        // word grid
        body.add(wordGrid);
        refresh();
    }

    private void start() {
        Map<String, List<String>> params = new HashMap<>();
        // add params as necessary
        params.put("latin", Collections.singletonList(Boolean.toString(isLatin())));
        if (nQs.isEnabled() && nQs.getValue() != null) {
            if (nQs.getValue() > 0) {
                params.put("n", Collections.singletonList(Integer.toString(nQs.getValue())));
            }
        }
        if (time.isEnabled() && time.getValue() != null) {
            if (time.getValue() > 0) {
                params.put("t", Collections.singletonList(Double.toString(time.getValue())));
            }
        }
        if (timePQ.isEnabled() && timePQ.getValue() != null) {
            if (timePQ.getValue() > 0) {
                params.put("tpq", Collections.singletonList(Integer.toString(timePQ.getValue())));
            }
        }
        if (type.isEmpty()) {
            type.setInvalid(true);
            return;
        } else {
            switch (type.getValue()) {
                case "Multiple Choice" -> params.put("type", Collections.singletonList("mcq"));
                case "Type-in" -> params.put("type", Collections.singletonList("type"));
            }
        }
        params.put("filter", Collections.singletonList(getSelected().name()));
        params.put("sel", getSelected().selection());

        // go to test view
        go.getUI().ifPresent(ui -> ui.navigate("test", new QueryParameters(params)));
    }

    public void refresh() {
        // get and set words from customizer
        var ws = getSelected().get();
        wordGrid.setItems(ws);
        wordGrid.setColumns("latin", "english", "type", "stage");
        wordGrid.getColumnByKey("stage").setFlexGrow(0);

        // update word count
        wordCount.setText(String.format("%d words", ws.size()));
    }

    private Customizer getSelected() {
        for (var c : container.getChildren().toArray()) {
            if (((Component) c).isVisible()) {
                return (Customizer) c;
            }
        }
        return new All();
    }

    public boolean isLatin() {
        return testDirectionIcon.getProperty("icon").equals("vaadin:arrow-right");
    }
}
