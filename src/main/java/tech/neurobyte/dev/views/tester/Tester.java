/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Tester.java
 * Last Modified: 10/04/2021, 19:55
 */

package tech.neurobyte.dev.views.tester;

import tech.neurobyte.dev.data.Word;

public interface Tester {
    void nextWord(Word w);

    void setCallback(java.util.function.Consumer<Void> e);
}
