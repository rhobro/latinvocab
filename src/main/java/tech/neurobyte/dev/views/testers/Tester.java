/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Tester.java
 * Last Modified: 10/04/2021, 22:13
 */

package tech.neurobyte.dev.views.testers;

import tech.neurobyte.dev.data.Word;

import java.util.List;
import java.util.function.Consumer;

public interface Tester {
    void nextWord(List<Word> ws, int i);

    void setCallback(Consumer<Void> e);

    void setLang(boolean latin);
}
