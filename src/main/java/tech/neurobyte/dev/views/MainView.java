/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MainView.java
 * Last Modified: 04/04/2021, 18:02
 */

package tech.neurobyte.dev.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.vaadin.tabs.PagedTabs;
import tech.neurobyte.dev.customizers.All;
import tech.neurobyte.dev.customizers.Customizer;
import tech.neurobyte.dev.data.Filter;
import tech.neurobyte.dev.data.Word;

import java.time.LocalDate;
import java.util.List;
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
@JsModule("./views/main/main-view.js")
public class MainView extends PolymerTemplate<MainView.MainViewModel> {

    // internal
    private final Grid<Word> wordGrid = new Grid<>(Word.class);
    // components
    @Id("body")
    private VerticalLayout body;
    // tester params
    @Id("customizer")
    private VerticalLayout customizer;

    @Id("testDirection")
    private Button testDirection;

    @Id("nQs")
    private IntegerField nQs;
    @Id("unlimQs")
    private Button unlimQs;

    @Id("time")
    private NumberField time;
    @Id("unlimT")
    private Button unlimT;

    @Id("timePQ")
    private IntegerField timePQ;
    @Id("unlimTPQ")
    private Button unlimTPQ;

    @Id("cpyr")
    private H6 cpyr;

    // internal components
    private final PagedTabs tabs;

    // internal
    private final Customizer all = new All();

    /**
     * Creates a new MainView.
     */
    public MainView() {
        // setup tabs
        var container = new VerticalLayout();
        tabs = new PagedTabs(container);
        tabs.add("All", (Component) all, false);
        tabs.add("By Stage", new H3("stage"), false);
        tabs.add("By Letter", new H3("letter"), false);
        tabs.add("By Type", new H3("type"), false);
        customizer.add(tabs, container);
        // equally space tabs
        tabs.getChildren().findFirst().ifPresent(c -> c.getChildren().forEach(e -> ((Tab) e).setFlexGrow(1)));

        // setup params
        // test direction
        testDirection.setIcon(new Icon(VaadinIcon.ARROW_RIGHT));
        testDirection.addClickListener(e -> {
            switch (testDirection.getIcon().getElement().getAttribute("icon")) {
                case "vaadin:arrow-right" -> testDirection.setIcon(new Icon(VaadinIcon.ARROW_LEFT));
                case "vaadin:arrow-left" -> testDirection.setIcon(new Icon(VaadinIcon.ARROW_RIGHT));
            }
        });

        var disableToF = Map.of(
                unlimQs, nQs,
                unlimT, time,
                unlimTPQ, timePQ
        );
        for (var b : disableToF.keySet()) {
            b.setIcon(new Icon(VaadinIcon.BAN));
            b.addClickListener(e -> disableToF.get(b).setEnabled(!disableToF.get(b).getElement().isEnabled()));
        }
        unlimT.click();
        unlimTPQ.click();

        // word grid
        body.add(wordGrid);
        updateWordTable(Filter.all());

        // set new copyright
        cpyr.setText(String.format("Copyright © %d Rohan Mathew. All rights reserved.", LocalDate.now().getYear()));
    }

    private void updateWordTable(List<Word> words) {
        wordGrid.setItems(words);
        wordGrid.setColumns("latin", "english", "type", "stage");
        wordGrid.getColumnByKey("stage").setFlexGrow(0);
    }

    /**
     * This model binds properties between MainView and main-view
     */
    public interface MainViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
