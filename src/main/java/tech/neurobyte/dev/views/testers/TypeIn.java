/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: TypeIn.java
 * Last Modified: 23/05/2021, 13:00
 */

package tech.neurobyte.dev.views.testers;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import tech.neurobyte.dev.data.Word;

@Tag("type-in")
@JsModule("./views/testers/type-in.ts")
public class TypeIn extends LitTemplate implements Tester {

    // components
    @Id("field")
    private TextField field;

    // internal
    private boolean latin;
    private Word c;

    // callbacks
    private Runnable onCorrect;
    private Runnable onIncorrect;
    private Runnable onAnswer;

    public TypeIn() {
        field.addKeyPressListener(Key.ENTER, e -> {
            if ((latin ? c.aEn : c.aLa).contains(field.getValue())) {
                // correct
                if (onCorrect != null) onCorrect.run();

            } else {
                // incorrect
                if (onIncorrect != null) onIncorrect.run();
                field.setInvalid(true); // invalid colour
                field.clear(); // clear field
                // show answer
                Notification.show(latin ? c.getEnglishAns() : c.getLatinAns(), 5000, Notification.Position.TOP_CENTER);
            }

            // general callback
            if (onAnswer != null) onAnswer.run();
        });
    }

    @Override
    public void nextWord(Word w) {
        c = w;

        // clear text
        field.setInvalid(false);
        field.clear();
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
        return field.isEnabled();
    }

    @Override
    public void setEnabled(boolean enable) {
        field.setEnabled(enable);
    }
}
