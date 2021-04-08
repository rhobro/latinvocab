/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: ByLetter.java
 * Last Modified: 08/04/2021, 12:19
 */

package tech.neurobyte.dev.views.customizers;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import tech.neurobyte.dev.data.Filter;
import tech.neurobyte.dev.data.Word;
import tech.neurobyte.dev.utils.Const;
import tech.neurobyte.dev.utils.Str;
import tech.neurobyte.dev.views.MainView;

import java.util.List;

@Tag("by-letter")
@JsModule("./views/customizers/by-letter.ts")
public class ByLetter extends LitTemplate implements Customizer {

    // internal
    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // components
    @Id("toggles")
    private HorizontalLayout toggles;
    // range selector
    @Id("rgsLBound")
    private ComboBox<String> rgsLBound;
    @Id("rgsUBound")
    private ComboBox<String> rgsUBound;
    @Id("rgsApply")
    private Button rgsApply;
    @Id("selAll")
    private Button selAll;
    // range deselector
    @Id("rgdLBound")
    private ComboBox<String> rgdLBound;
    @Id("rgdUBound")
    private ComboBox<String> rgdUBound;
    @Id("rgdApply")
    private Button rgdApply;
    @Id("deselAll")
    private Button deselAll;

    public ByLetter() {
        // toggle setup
        for (var alpha : ALPHABETS.split("")) {
            var b = new Button(alpha);
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
        rgsLBound.setItems(Str.splitCollection(ALPHABETS.substring(0, 26)));
        rgsLBound.addValueChangeListener(e -> {
            // check with u bound
            if (ALPHABETS.indexOf(e.getValue()) >= ALPHABETS.indexOf(rgsUBound.getValue())) {
                // step up u bound
                rgsUBound.setValue(Character.toString(e.getValue().charAt(0) + 1));
            }
        });
        rgsUBound.setItems(Str.splitCollection(ALPHABETS.substring(1)));
        rgsUBound.addValueChangeListener(e -> {
            // check with l bound
            if (ALPHABETS.indexOf(e.getValue()) <= ALPHABETS.indexOf(rgsUBound.getValue())) {
                // step down l bound
                rgsLBound.setValue(Character.toString(e.getValue().charAt(0) - 1));
            }
        });
        rgsApply.addClickListener(e -> {
            // loop through and select
            toggles.getChildren().forEach(t -> {
                var c = ((Button) t).getText().toCharArray()[0];
                if (c >= rgsLBound.getValue().charAt(0) && c <= rgsUBound.getValue().charAt(0)) {
                    ((Button) t).addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                }
            });

            MainView.main.refresh();
        });
        selAll.addClickListener(e -> {
            toggles.getChildren().forEach(b -> ((Button) b).addThemeVariants(ButtonVariant.LUMO_PRIMARY));

            MainView.main.refresh();
        });

        // range deselector
        rgdLBound.setItems(Str.splitCollection(ALPHABETS.substring(0, 26)));
        rgdLBound.addValueChangeListener(e -> {
            // check with u bound
            if (ALPHABETS.indexOf(e.getValue()) >= ALPHABETS.indexOf(rgdUBound.getValue())) {
                // step up u bound
                rgdUBound.setValue(Character.toString(e.getValue().charAt(0) + 1));
            }
        });
        rgdUBound.setItems(Str.splitCollection(ALPHABETS.substring(1)));
        rgdUBound.addValueChangeListener(e -> {
            // check with l bound
            if (ALPHABETS.indexOf(e.getValue()) <= ALPHABETS.indexOf(rgdUBound.getValue())) {
                // step down l bound
                rgdLBound.setValue(Character.toString(e.getValue().charAt(0) - 1));
            }
        });
        rgdApply.addClickListener(e -> {
            // loop through and select
            toggles.getChildren().forEach(t -> {
                var c = ((Button) t).getText().toCharArray()[0];
                if (c >= rgdLBound.getValue().charAt(0) && c <= rgdUBound.getValue().charAt(0)) {
                    ((Button) t).removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
                }
            });

            MainView.main.refresh();
        });
        deselAll.addClickListener(e -> {
            toggles.getChildren().forEach(b -> ((Button) b).removeThemeVariants(ButtonVariant.LUMO_PRIMARY));

            MainView.main.refresh();
        });
    }

    @Override
    public List<Word> get() {
        StringBuilder letters = new StringBuilder();

        // loop through toggles and add idxs
        for (var e : toggles.getChildren().toArray()) {
            var b = (Button) e;
            if (b.getThemeNames().contains("primary")) {
                // selected
                letters.append(((Button) e).getText().toLowerCase())
                        .append(((Button) e).getText().toUpperCase());
            }
        }

        if (letters.length() > 0) {
            return Filter.byLetter(MainView.main.getIsLatin(), letters.toString());
        }
        return Filter.empty();
    }

}
