/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: ByStage.java
 * Last Modified: 08/04/2021, 12:19
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
import tech.neurobyte.dev.data.Filter;
import tech.neurobyte.dev.data.Word;
import tech.neurobyte.dev.utils.Const;
import tech.neurobyte.dev.views.MainView;

import java.util.ArrayList;
import java.util.List;

@Tag("by-stage")
@JsModule("./views/customizers/by-stage.ts")
public class ByStage extends LitTemplate implements Customizer {

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
    @Id("selAll")
    private Button selAll;
    // range deselector
    @Id("rgdLBound")
    private IntegerField rgdLBound;
    @Id("rgdUBound")
    private IntegerField rgdUBound;
    @Id("rgdApply")
    private Button rgdApply;
    @Id("deselAll")
    private Button deselAll;

    public ByStage() {
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

                MainView.main.refresh();
            });
            b.addThemeVariants(ButtonVariant.LUMO_ICON);
            b.getStyle().set("border-radius", Const.TOGGLE_BORDER_RADIUS);

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

            MainView.main.refresh();
        });
        selAll.addClickListener(e -> {
            toggles.getChildren().forEach(b -> {
                ((Button) b).addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            });

            MainView.main.refresh();
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

            MainView.main.refresh();
        });
        deselAll.addClickListener(e -> {
            toggles.getChildren().forEach(b -> {
                ((Button) b).removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
            });

            MainView.main.refresh();
        });
    }

    @Override
    public List<Word> get() {
        var stages = new ArrayList<Integer>();

        // loop through toggles and add idxs
        toggles.getChildren().forEach(e -> {
            var b = (Button) e;
            if (b.getThemeNames().contains("primary")) {
                // selected
                var label = ((Button) e).getText();
                stages.add(label.equals("Non-CLC") ? 0 : Integer.parseInt(label));
            }
        });

        if (stages.size() > 0) {
            return Filter.byStage(stages);
        }
        return Filter.empty();
    }
}
