/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MainView.java
 * Last Modified: 06/04/2021, 21:09
 */

package tech.neurobyte.dev.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.vaadin.tabs.PagedTabs;
import tech.neurobyte.dev.data.Word;
import tech.neurobyte.dev.views.customizers.All;
import tech.neurobyte.dev.views.customizers.ByStage;
import tech.neurobyte.dev.views.customizers.Customizer;

import java.time.LocalDate;
import java.util.Map;

/**
 * A Designer generated component for the main-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PWA(name = "Latin Vocab", shortName = "Latin")
@Route("")
@Tag("main-view")
@JsModule("./views/main-view.ts")
public class MainView extends LitTemplate {

    // internal
    private final Grid<Word> wordGrid = new Grid<>(Word.class);
    public static MainView main;
    @Id("body")
    private VerticalLayout body;
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

    @Id("cpyr")
    private H6 cpyr;
    // internal components
    private final VerticalLayout container = new VerticalLayout();
    private final PagedTabs tabs = new PagedTabs(container);

    /**
     * Creates a new MainView.
     */
    public MainView() {
        main = this;

        // setup tabs
        tabs.add("All", new All(), false);
        tabs.add("By Stage", new ByStage(), false);
        tabs.add("By Letter", new H3("letter"), false);
        tabs.add("By Type", new H3("type"), false);
        // equally space tabs
        tabs.getChildren().findFirst().ifPresent(c -> c.getChildren().forEach(e -> ((Tab) e).setFlexGrow(1)));
        // refresh on tab switch
        tabs.addSelectedChangeListener(e -> refresh());
        // add to layout
        customizer.add(tabs, container);

        // setup params
        // test direction
        testDirection.addClickListener(e -> {
            switch (testDirectionIcon.getProperty("icon")) {
                case "vaadin:arrow-right" -> testDirectionIcon.setProperty("icon", "vaadin:arrow-left");
                case "vaadin:arrow-left" -> testDirectionIcon.setProperty("icon", "vaadin:arrow-right");
            }
        });

        // disables
        var disableToF = Map.of(
                unlimQs, nQs,
                unlimT, time,
                unlimTPQ, timePQ
        );
        for (var b : disableToF.keySet()) {
            b.addClickListener(e -> e.getSource().setEnabled(!e.getSource().getElement().isEnabled()));
        }
        unlimT.click();
        unlimTPQ.click();

        // word grid
        body.add(wordGrid);
        refresh();

        // set new copyright
        cpyr.setText(String.format("Copyright © %d Rohan Mathew. All rights reserved.", LocalDate.now().getYear()));
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
}
