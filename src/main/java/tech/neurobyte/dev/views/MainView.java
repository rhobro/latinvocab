/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MainView.java
 * Last Modified: 03/04/2021, 21:15
 */

package tech.neurobyte.dev.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
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
import tech.neurobyte.dev.data.Filter;
import tech.neurobyte.dev.data.Word;

import java.time.LocalDate;
import java.util.List;

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

    /**
     * Creates a new MainView.
     */
    public MainView() {
        // setup tabs
        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);
        var t = tabs.add("All", new Div(), false);
        t = tabs.add("By Stage", new H3("stage"), false);
        t = tabs.add("By Letter", new H3("letter"), false);
        t = tabs.add("By Type", new H3("type"), false);
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
        // number of questions
        unlimQs.setIcon(new Icon(VaadinIcon.BAN));
        unlimQs.addClickListener(e -> {
            nQs.setEnabled(!nQs.getElement().isEnabled());
        });
        // time
        unlimT.setIcon(new Icon(VaadinIcon.BAN));
        unlimT.addClickListener(e -> {
            time.setEnabled(!time.getElement().isEnabled());
        });
        unlimT.click();
        // time per questions
        unlimTPQ.setIcon(new Icon(VaadinIcon.BAN));
        unlimTPQ.addClickListener(e -> {
            timePQ.setEnabled(!timePQ.getElement().isEnabled());
        });
        unlimTPQ.click();

        // word grid
        body.add(wordGrid);
        updateWordTable(Filter.all());

        // set new copyright
        cpyr.setText(String.format("Copyright © %d Rohan Mathew. All rights reserved.", LocalDate.now().getYear()));
    }

    private List<Word> getFiltered() {
        return null;
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
