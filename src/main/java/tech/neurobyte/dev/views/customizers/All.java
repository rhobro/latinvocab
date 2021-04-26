/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: All.java
 * Last Modified: 26/04/2021, 21:14
 */

package tech.neurobyte.dev.views.customizers;

import com.vaadin.flow.component.html.Div;
import tech.neurobyte.dev.data.Data;
import tech.neurobyte.dev.data.Word;

import java.util.Collections;
import java.util.List;

public class All extends Div implements Customizer {

    @Override
    public List<Word> get() {
        Collections.shuffle(Data.words);
        return Data.words;
    }

    @Override
    public List<String> selection() {
        return Collections.emptyList();
    }

    @Override
    public String name() {
        return "all";
    }
}
