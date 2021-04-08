/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MainView.java
 * Last Modified: 08/04/2021, 21:32
 */

package tech.neurobyte.dev.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
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

    // internal components
    private final VerticalLayout container = new VerticalLayout();
    private final PagedTabs tabs = new PagedTabs(container);
    private final Grid<Word> wordGrid = new Grid<>(Word.class);
    // components
    @Id("body")
    private VerticalLayout body;

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
            switch (testDirectionIcon.getProperty("icon")) {
                case "vaadin:arrow-right" -> testDirectionIcon.setProperty("icon", "vaadin:arrow-left");
                case "vaadin:arrow-left" -> testDirectionIcon.setProperty("icon", "vaadin:arrow-right");
            }

            refresh();
        });

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
        }
        unlimT.click();
        unlimTPQ.click();

        // setup go button
        go.addClickListener(e -> start());

        // word grid
        body.add(wordGrid);
        refresh();
    }

    private void start() {
        Map<String, List<String>> params = new HashMap<>();
        // add params as necessary
        params.put("latin", Collections.singletonList(Boolean.toString(getIsLatin())));
        if (nQs.isEnabled() && nQs.getValue() > 0) {
            params.put("n", Collections.singletonList(Integer.toString(nQs.getValue())));
        }
        if (time.isEnabled() && time.getValue() > 0) {
            params.put("n", Collections.singletonList(Double.toString(time.getValue())));
        }
        if (timePQ.isEnabled() && timePQ.getValue() > 0) {
            params.put("n", Collections.singletonList(Integer.toString(timePQ.getValue())));
        }

        // go to test view
        go.getUI().ifPresent(ui -> ui.navigate("test", new QueryParameters(params)));
    }

    public void refresh() {
        // get and set words from customizer
        container.getChildren().forEach(custom -> {
            if (custom.isVisible()) {
                wordGrid.setItems(((Customizer) custom).get());
                wordGrid.setColumns("latin", "english", "type", "stage");
                wordGrid.getColumnByKey("stage").setFlexGrow(0);
            }
        });
    }

    public boolean getIsLatin() {
        return testDirectionIcon.getProperty("icon").equals("vaadin:arrow-right");
    }
}
