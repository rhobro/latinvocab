/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Customizer.java
 * Last Modified: 09/04/2021, 11:48
 */

package tech.neurobyte.dev.views.customizers;

import tech.neurobyte.dev.data.Word;

import java.util.List;

public interface Customizer {
    List<Word> get();

    String name();

    List<String> selection();
}
