/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Tester.java
 * Last Modified: 24/04/2021, 22:39
 */

package tech.neurobyte.dev.views.testers;

import tech.neurobyte.dev.data.Word;

public interface Tester {
    void nextWord(Word w);

    void setOnAnswer(Runnable e);

    void setOnCorrect(Runnable e);

    void setLang(boolean latin);

    boolean isEnabled();

    void setEnabled(boolean enable);
}
