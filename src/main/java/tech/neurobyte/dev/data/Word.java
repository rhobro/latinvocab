/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Word.java
 * Last Modified: 26/04/2021, 21:23
 */

package tech.neurobyte.dev.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Word {
    public final String qLa;
    public final List<String> aLa = new ArrayList<>();
    public final String qEn;
    public final List<String> aEn = new ArrayList<>();
    public final List<String> type = new ArrayList<>();
    public final int stage;

    private static final Map<String, String> typeMap = Map.of(
            "v", "verb",
            "n", "noun",
            "a", "adjective",
            "p", "preposition",
            "r", "pronoun",
            "d", "adverb",
            "x", "misc"
    );

    public Word(String raw) {
        var l = raw.split("#");

        // set member fields
        qLa = l[0];
        Collections.addAll(aLa, l[3].split(":"));
        qEn = l[1];
        Collections.addAll(aEn, l[4].split(":"));
        for (var c : l) {
            type.add(typeMap.get(c));
        }
        stage = Integer.parseInt(l[5]);
    }

    // getters

    public String getLatin() {
        return qLa;
    }

    public String getEnglish() {
        return qEn;
    }

    public String getType() {
        return String.join(", ", type);
    }

    public int getStage() {
        return stage;
    }
}
