/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MultipleChoice.java
 * Last Modified: 10/04/2021, 22:20
 */

package tech.neurobyte.dev.views.testers;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import tech.neurobyte.dev.data.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Tag("multiple-choice")
@JsModule("./views/testers/multiple-choice.ts")
public class MultipleChoice extends LitTemplate implements Tester {

    // internal
    private final List<Button> opts = new ArrayList<>();
    // components
    @Id("root")
    private VerticalLayout root;
    @Id("opt1")
    private Button opt1;
    @Id("opt2")
    private Button opt2;
    @Id("opt3")
    private Button opt3;
    @Id("opt4")
    private Button opt4;

    // internal
    private boolean latin;
    private Word c;

    public MultipleChoice() {
        opts.addAll(Arrays.asList(opt1, opt2, opt3, opt4));
        // setup choice listeners
        opts.forEach(o -> {
            o.addClickListener(e -> {
                if ((latin ? c.aEn : c.aLa).contains(e.getSource().getText())) {
                    // if correct
                    // colour success
                    e.getSource().addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                } else {
                    // incorrect
                    // colour error
                    e.getSource().addThemeVariants(ButtonVariant.LUMO_ERROR);
                }

                // disable all buttons
                opts.forEach(b -> b.setEnabled(false));
            });
        });
    }

    @Override
    public void nextWord(List<Word> ws, int i) {
        // reset colours
        opts.forEach(o -> {
            o.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
            o.setEnabled(true);
        });
        // answers
    }

    @Override
    public void setLang(boolean latin) {
        this.latin = latin;
    }

    @Override
    public void setCallback(Consumer<Void> e) {

    }
}
