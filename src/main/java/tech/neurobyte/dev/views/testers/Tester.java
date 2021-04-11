/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Tester.java
 * Last Modified: 11/04/2021, 10:41
 */

package tech.neurobyte.dev.views.testers;

import tech.neurobyte.dev.data.Word;

import java.util.function.Consumer;

public interface Tester {
    void nextWord(Word w);

    void setCallback(Consumer<Void> e);

    void setLang(boolean latin);
}
