/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: TestView.java
 * Last Modified: 09/04/2021, 11:53
 */

package tech.neurobyte.dev.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import tech.neurobyte.dev.data.Filter;
import tech.neurobyte.dev.data.Word;

import java.util.ArrayList;
import java.util.List;

@Route("test")
@Tag("test-view")
@JsModule("./views/test-view.ts")
public class TestView extends LitTemplate implements HasUrlParameter<String> {

    // components

    // params
    private boolean latin = true;
    private int nQs = -1;
    private double time = -1;
    private int timePQ = -1;
    private List<Word> words;
    private boolean invalidURL = false;

    public TestView() {
        if (invalidURL) {
            // popup
        }

    }

    @Override
    public void setParameter(BeforeEvent e, @OptionalParameter String s) {
        var loc = e.getLocation();
        var queryParams = loc.getQueryParameters();
        var params = queryParams.getParameters();

        // check for necessary values
        if (!params.containsKey("latin") ||
                !params.containsKey("sel") ||
                !params.containsKey("filter")) {
            invalidURL = true;
            return;
        }

        latin = Boolean.parseBoolean(params.get("latin").get(0));
        if (params.containsKey("n")) {
            nQs = Integer.parseInt(params.get("n").get(0));
        }
        if (params.containsKey("t")) {
            time = Double.parseDouble(params.get("t").get(0));
        }
        if (params.containsKey("tpq")) {
            timePQ = Integer.parseInt(params.get("tpq").get(0));
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
    }
}
