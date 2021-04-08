/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: All.java
 * Last Modified: 08/04/2021, 21:51
 */

package tech.neurobyte.dev.views.customizers;

import com.vaadin.flow.component.html.Div;
import tech.neurobyte.dev.data.DB;
import tech.neurobyte.dev.data.Word;

import java.util.Collections;
import java.util.List;

public class All extends Div implements Customizer {

    @Override
    public List<Word> get() {
        var filtrate = DB.words.find();
        return Word.parse(filtrate);
    }

    @Override
    public List<String> routeOpt() {
        return Collections.emptyList();
    }

    @Override
    public String routeSel() {
        return "all";
    }
}
