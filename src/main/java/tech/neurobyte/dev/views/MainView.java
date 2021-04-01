/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MainView.java
 * Last Modified: 01/04/2021, 21:08
 */

package tech.neurobyte.dev.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
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
    @Id("cpyr")
    private H6 cpyr;
    @Id("customizer")
    private VerticalLayout customizer;

    /**
     * Creates a new MainView.
     */
    public MainView() {
        // setup tabs
        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);
        tabs.add("All", new H3("all"), false);
        tabs.add("By Stage", new H3("stage"), false);
        tabs.add("By Letter", new H3("letter"), false);
        customizer.add(tabs, container);

        // word grid
        body.add(wordGrid);
        wordGrid.setMultiSort(true);
        wordGrid.getColumnByKey("type").setFlexGrow(0);
        wordGrid.getColumnByKey("stage").setFlexGrow(0);
        updateWordTable(Filter.all());

        // set new copyright
        cpyr.setText(String.format("Copyright © %d Rohan Mathew. All rights reserved.", LocalDate.now().getYear()));
    }

    private void updateWordTable(List<Word> words) {
        wordGrid.setItems(words);
        wordGrid.setColumns("latin", "english", "type", "stage");
    }

    /**
     * This model binds properties between MainView and main-view
     */
    public interface MainViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
