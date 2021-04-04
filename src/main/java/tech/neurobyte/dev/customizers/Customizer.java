/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Customizer.java
 * Last Modified: 04/04/2021, 14:12
 */

package tech.neurobyte.dev.customizers;

import tech.neurobyte.dev.data.Word;

import java.util.List;

public interface Customizer {
    List<Word> get();
}
