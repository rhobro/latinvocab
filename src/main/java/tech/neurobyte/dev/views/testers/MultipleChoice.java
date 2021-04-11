/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MultipleChoice.java
 * Last Modified: 11/04/2021, 13:40
 */

package tech.neurobyte.dev.views.testers;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import tech.neurobyte.dev.data.Filter;
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
                    e.getSource().addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS); // colour success
                }

                // disable others
                opts.forEach(b -> {
                    if (b != e.getSource()) {
                        b.setEnabled(false);
                    }
                });
            });
        });
    }

    @Override
    public void nextWord(Word w) {
        c = w;
        // reset colours
        opts.forEach(o -> {
            o.removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
            o.setEnabled(true);
        });
        // set rand options + answer
        var rand = Filter.rand(opts.size()); // get rand options
        var count = 0;
        var correct = (int) (Math.random() * opts.size()); // option index to store correct answer
        for (var o : opts) {
            if (count == correct) {
                o.setText(latin ? c.aEn.get(0) : c.aLa.get(0));
            } else {
                o.setText(latin ? rand.get(count).qEn : rand.get(count).qLa);
            }
            count++;
        }
    }

    @Override
    public void setLang(boolean latin) {
        this.latin = latin;
    }

    @Override
    public void setCallback(Consumer<Void> e) {

    }
}
