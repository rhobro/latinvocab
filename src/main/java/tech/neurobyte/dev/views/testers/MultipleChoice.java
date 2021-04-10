/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: MultipleChoice.java
 * Last Modified: 10/04/2021, 20:54
 */

package tech.neurobyte.dev.views.testers;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import tech.neurobyte.dev.data.Word;

import java.util.function.Consumer;

/**
 * A Designer generated component for the multiple-choice template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("multiple-choice")
@JsModule("./views/testers/multiple-choice.ts")
public class MultipleChoice extends LitTemplate implements Tester {

    /**
     * Creates a new MultipleChoice.
     */
    public MultipleChoice() {
        // You can initialise any data required for the connected UI components here.
    }

    @Override
    public void nextWord(Word w) {

    }

    @Override
    public void setCallback(Consumer<Void> e) {

    }
}
