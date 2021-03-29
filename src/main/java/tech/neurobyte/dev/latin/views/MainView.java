/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MainView.java
 * Last Modified: 29/03/2021, 20:40
 */

package tech.neurobyte.dev.latin.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.time.LocalDate;

/**
 * A Designer generated component for the main-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@PWA(name = "Latin Vocab", shortName = "Latin")
@Route("")
@Tag("main-view")
@JsModule("./views/main/main-view.js")
public class MainView extends PolymerTemplate<MainView.MainViewModel> {

    @Id("cpyr")
    private H6 cpyr;

    /**
     * Creates a new MainView.
     */
    public MainView() {
        cpyr.setText(String.format("Copyright © %d Rohan Mathew. All rights reserved.", LocalDate.now().getYear()));
    }

    /**
     * This model binds properties between MainView and main-view
     */
    public interface MainViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
