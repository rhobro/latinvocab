/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MultipleChoice.java
 * Last Modified: 30/04/2021, 20:39
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

    // callbacks
    private Runnable onCorrect;
    private Runnable onIncorrect;
    private Runnable onAnswer;

    public MultipleChoice() {
        opts.addAll(Arrays.asList(opt1, opt2, opt3, opt4));
        // setup choice listeners
        opts.forEach(o -> o.addClickListener(e -> {
            // if not clicked yet
            if (!e.getSource().hasThemeName("primary")) {
                // disable others
                opts.forEach(b -> {
                    // deselect if not option and if not correct answer
                    if (b != e.getSource() && (latin ? c.getEnglishAns() : c.getLatinAns()).equals(b.getText())) {
                        b.setEnabled(false);
                    }
                });

                if ((latin ? c.getEnglishAns() : c.getLatinAns()).equals(e.getSource().getText())) {
                    // if correct
                    e.getSource().addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS); // colour success
                    // callback if correct
                    onCorrect.run();
                } else {
                    // if incorrect
                    e.getSource().addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR); // colour error
                    // callback if incorrect
                    onIncorrect.run();
                }

                // general callback
                onAnswer.run();
            }
        }));
    }

    @Override
    public void nextWord(Word w) {
        c = w;

        // reset colours
        opts.forEach(o -> o.removeThemeVariants(
                ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_SUCCESS,
                ButtonVariant.LUMO_ERROR));
        setEnabled(true);

        // set rand options + answer
        var rand = Filter.rand(opts.size()); // get rand options
        var count = 0;
        var correct = (int) (Math.random() * opts.size()); // option index to store correct answer
        for (var o : opts) {
            if (count == correct) {
                o.setText(latin ? c.getEnglishAns() : c.getLatinAns());
            } else {
                o.setText(latin ? rand.get(count).getEnglishAns() : rand.get(count).getLatinAns());
            }
            count++;
        }
    }

    @Override
    public void setLang(boolean latin) {
        this.latin = latin;
    }

    @Override
    public void setOnCorrect(Runnable e) {
        onCorrect = e;
    }

    @Override
    public void setOnIncorrect(Runnable e) {
        onIncorrect = e;
    }

    @Override
    public void setOnAnswer(Runnable e) {
        onAnswer = e;
    }

    @Override
    public boolean isEnabled() {
        return opts.get(0).isEnabled();
    }

    @Override
    public void setEnabled(boolean enable) {
        opts.forEach(o -> o.setEnabled(enable));
    }
}
