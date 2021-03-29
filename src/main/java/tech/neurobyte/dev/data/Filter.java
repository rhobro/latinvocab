/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Filter.java
 * Last Modified: 29/03/2021, 21:28
 */

package tech.neurobyte.dev.data;

import tech.neurobyte.dev.utils.Str;

import java.util.ArrayList;

public class Filter {
    public static ArrayList<Word> byStage(ArrayList<Integer> stages) {
        var sql = String.format("""
                SELECT *
                FROM vocab
                WHERE stage IN (%s);""", Str.join(", ", stages));
        return Word.list(DB.query(sql));
    }

    /*public static ArrayList<Word> byLetter(boolean inLatin, ArrayList<Character> alphas) {
        var conds = new ArrayList<String>();
        for

        var sql = String.format("""
            SELECT *
            FROM vocab
            WHERE %s LIKE ;""", inLatin ? "latin" : "english");
        return Word.list(DB.query(sql));
    }*/
}
