/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Filter.java
 * Last Modified: 31/03/2021, 19:59
 */

package tech.neurobyte.dev.data;

import java.util.List;

import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.regex;

public class Filter {
    public static List<Word> byStage(List<Integer> stages) {
        var filtrate = DB.words.find(in("stage", stages));
        return Word.parse(filtrate);
    }

    public static List<Word> byLetter(boolean inLatin, String alphas) {
        var field = inLatin ? "qLatin" : "qEnglish";
        var filtrate = DB.words.find(regex(field, "^[" + alphas + "]"));
        return Word.parse(filtrate);
    }

    public static List<Word> all() {
        var filtrate = DB.words.find();
        return Word.parse(filtrate);
    }
}
