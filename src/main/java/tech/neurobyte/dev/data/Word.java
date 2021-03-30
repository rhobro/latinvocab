/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Word.java
 * Last Modified: 30/03/2021, 21:38
 */

package tech.neurobyte.dev.data;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word {
    public final String qEng;
    public final List<String> aEng;
    public final String qLa;
    public final List<String> aLa;
    public final String type;
    public final int stage;

    public Word(Document entry) {
        qEng = entry.getString("qEnglish");
        aEng = entry.getList("aEnglish", String.class);
        qLa = entry.getString("qLatin");
        aLa = entry.getList("aLatin", String.class);
        type = entry.getString("type");
        stage = entry.getInteger("stage");
    }

    public static ArrayList<Word> parse(FindIterable<Document> docs) {
        var words = new ArrayList<Word>();
        docs.forEach(d -> words.add(new Word(d)));
        Collections.shuffle(words);
        return words;
    }
}
