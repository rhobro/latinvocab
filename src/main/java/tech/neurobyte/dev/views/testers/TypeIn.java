/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: TypeIn.java
 * Last Modified: 10/04/2021, 20:54
 */

package tech.neurobyte.dev.views.testers;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import tech.neurobyte.dev.data.Word;

import java.util.function.Consumer;

/**
 * A Designer generated component for the type-in template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("type-in")
@JsModule("./views/testers/type-in.ts")
public class TypeIn extends LitTemplate implements Tester {

    /**
     * Creates a new TypeIn.
     */
    public TypeIn() {
        // You can initialise any data required for the connected UI components here.
    }

    @Override
    public void nextWord(Word w) {

    }

    @Override
    public void setCallback(Consumer<Void> e) {

    }
}
