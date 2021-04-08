/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: ByType.java
 * Last Modified: 08/04/2021, 21:31
 */

package tech.neurobyte.dev.views.customizers;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import tech.neurobyte.dev.data.DB;
import tech.neurobyte.dev.data.Filter;
import tech.neurobyte.dev.data.Word;
import tech.neurobyte.dev.utils.Const;
import tech.neurobyte.dev.views.MainView;

import java.util.ArrayList;
import java.util.List;

@Tag("by-type")
@JsModule("./views/customizers/by-type.ts")
public class ByType extends LitTemplate implements Customizer {

    // components
    @Id("toggles")
    private HorizontalLayout toggles;
    @Id("selAll")
    private Button selAll;
    @Id("deselAll")
    private Button deselAll;

    public ByType() {
        // toggle setup
        for (var type : DB.getTypes()) {
            var b = new Button(type);
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

        // all selectors
        selAll.addClickListener(e -> {
            toggles.getChildren().forEach(b -> {
                ((Button) b).addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            });

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
        var types = routeOpt();
        if (types.size() > 0) {
            return Filter.byType(types);
        }
        return Filter.empty();
    }

    @Override
    public List<String> routeOpt() {
        List<String> types = new ArrayList<>();

        // loop through toggles and add idxs
        toggles.getChildren().forEach(e -> {
            var b = (Button) e;
            if (b.getThemeNames().contains("primary")) {
                // selected
                types.add(((Button) e).getText());
            }
        });

        return types;
    }

    @Override
    public String routeSel() {
        return "type";
    }
}
