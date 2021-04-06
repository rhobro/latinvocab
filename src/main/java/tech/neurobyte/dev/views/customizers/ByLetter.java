/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: ByLetter.java
 * Last Modified: 06/04/2021, 14:29
 */

package tech.neurobyte.dev.views.customizers;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.IntegerField;
import tech.neurobyte.dev.data.DB;
import tech.neurobyte.dev.data.Word;

import java.util.List;

/**
 * A Designer generated component for the by-stage template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("by-stage")
@JsModule("./views/customizers/by-stage.ts")
public class ByLetter extends LitTemplate implements Customizer {

    // internal
    private static final String TOGGLE_BORDER_RADIUS = "5px";
    // components
    @Id("toggles")
    private HorizontalLayout toggles;
    // range selector
    @Id("rgsLBound")
    private IntegerField rgsLBound;
    @Id("rgsUBound")
    private IntegerField rgsUBound;
    @Id("rgsApply")
    private Button rgsApply;
    // range deselector
    @Id("rgdLBound")
    private IntegerField rgdLBound;
    @Id("rgdUBound")
    private IntegerField rgdUBound;
    @Id("rgdApply")
    private Button rgdApply;

    public ByLetter() {
        // toggle setup
        for (int i = 0; i <= DB.getNStages(); i++) {
            var b = new Button(i == 0 ? "Non-CLC" : Integer.toString(i));
            b.addClickListener(e -> {
                if (e.getSource().getThemeNames().contains("primary")) {
                    // selected
                    e.getSource().removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
                } else {
                    // not selected
                    e.getSource().addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                }
            });
            b.addThemeVariants(ButtonVariant.LUMO_ICON);
            b.getStyle().set("border-radius", TOGGLE_BORDER_RADIUS);

            toggles.add(b);
        }
        toggles.getThemeList().clear();

        // range selector
        rgsLBound.addValueChangeListener(e -> {
            // check with u bound
            if (e.getValue().equals(rgsUBound.getValue())) {
                // step up u bound
                rgsUBound.setValue(e.getValue() + 1);
            }
        });
        rgsLBound.setMax(DB.getNStages() - 1);
        rgsUBound.addValueChangeListener(e -> {
            // check with l bound
            if (e.getValue().equals(rgsLBound.getValue())) {
                // step down l bound
                rgsLBound.setValue(e.getValue() - 1);
            }
        });
        rgsUBound.setMax(DB.getNStages());
        rgsApply.addClickListener(e -> {
            // select range
            var i = 0;
            for (var b : toggles.getChildren().toArray()) {
                if (i >= rgsLBound.getValue() && i <= rgsUBound.getValue()) {
                    ((Button) b).addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                }

                i++;
            }
        });

        // range deselector
        rgdLBound.addValueChangeListener(e -> {
            // check with u bound
            if (e.getValue().equals(rgdUBound.getValue())) {
                // step up u bound
                rgdUBound.setValue(e.getValue() + 1);
            }
        });
        rgdLBound.setMax(DB.getNStages() - 1);
        rgdUBound.addValueChangeListener(e -> {
            // check with l bound
            if (e.getValue().equals(rgdLBound.getValue())) {
                // step down l bound
                rgdLBound.setValue(e.getValue() - 1);
            }
        });
        rgdUBound.setMax(DB.getNStages());
        rgdApply.addClickListener(e -> {
            // select range
            var i = 0;
            for (var b : toggles.getChildren().toArray()) {
                if (i >= rgdLBound.getValue() && i <= rgdUBound.getValue()) {
                    ((Button) b).removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
                }

                i++;
            }
        });
    }

    @Override
    public List<Word> get() {
        return null;
    }
}
