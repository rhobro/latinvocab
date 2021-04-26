/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Filter.java
 * Last Modified: 26/04/2021, 21:23
 */

package tech.neurobyte.dev.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Filter {

    public static List<Word> all() {
        Collections.shuffle(Data.words);
        return Data.words;
    }

    public static List<Word> byStage(List<Integer> stages) {
        Collections.shuffle(Data.words);
        var filtrate = new ArrayList<Word>();
        for (var w : Data.words) {
            if (stages.contains(w.stage)) {
                filtrate.add(w);
            }
        }

        return filtrate;
    }

    public static List<Word> byLetter(boolean isLatin, String alphas) {
        Collections.shuffle(Data.words);
        var filtrate = new ArrayList<Word>();
        for (var w : Data.words) {
            if (isLatin) {
                if (alphas.contains(w.qLa.substring(0, 1))) {
                    filtrate.add(w);
                }
            } else {
                if (alphas.contains(w.qEn.substring(0, 1))) {
                    filtrate.add(w);
                }
            }
        }

        return filtrate;
    }

    public static List<Word> byType(List<String> types) {
        Collections.shuffle(Data.words);
        var filtrate = new ArrayList<Word>();
        for (var w : Data.words) {
            var add = false;

            for (var sT : types) {
                for (var wT : w.type) {
                    if (sT == wT) {
                        add = true;
                        break;
                    }
                }

                if (add) {
                    break;
                }
            }

            if (add) {
                filtrate.add(w);
            }
        }

        return filtrate;
    }

    public static List<Word> rand(int n) {
        return all().subList(0, n);
    }

    public static List<Word> empty() {
        return Collections.emptyList();
    }
}
