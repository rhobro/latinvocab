/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: All.java
 * Last Modified: 06/04/2021, 10:09
 */

package tech.neurobyte.dev.views.customizers;

import com.vaadin.flow.component.html.Div;
import tech.neurobyte.dev.data.DB;
import tech.neurobyte.dev.data.Word;

import java.util.List;

public class All extends Div implements Customizer {

    @Override
    public List<Word> get() {
        var filtrate = DB.words.find();
        return Word.parse(filtrate);
    }
}
