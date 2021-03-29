/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Word.java
 * Last Modified: 29/03/2021, 20:54
 */

package tech.neurobyte.dev.data;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.ArrayList;

public class Word {
    public final String latin;
    public final String english;
    public final String details;
    public final String grammar;
    public final int stage;

    public Word(Row entry) {
        latin = entry.getString("latin");
        english = entry.getString("english");
        details = entry.getString("details");
        grammar = entry.getString("grammar");
        stage = entry.getShort("stage");
    }

    public static ArrayList<Word> list(Table t) {
        assert t != null;
        t = t.dropDuplicateRows();

        var words = new ArrayList<Word>();
        for (int i = 0; i < t.rowCount(); i++) {
            words.add(new Word(t.row(i)));
        }
        return words;
    }
}
