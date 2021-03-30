/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Filter.java
 * Last Modified: 30/03/2021, 21:38
 */

package tech.neurobyte.dev.data;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.regex;

public class Filter {
    public static ArrayList<Word> byStage(ArrayList<Integer> stages) {
        var filtrate = DB.words.find(in("stage", stages));
        return Word.parse(filtrate);
    }

    public static ArrayList<Word> byLetter(boolean inLatin, String alphas) {
        var field = inLatin ? "qLatin" : "qEnglish";
        var filtrate = DB.words.find(regex(field, "[" + alphas + "]%"));
        return Word.parse(filtrate);
    }

    public static ArrayList<Word> all() {
        var filtrate = DB.words.find();
        return Word.parse(filtrate);
    }
}
