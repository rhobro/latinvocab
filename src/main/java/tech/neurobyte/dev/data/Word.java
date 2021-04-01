/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Word.java
 * Last Modified: 01/04/2021, 21:02
 */

package tech.neurobyte.dev.data;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word {
    public final String latin;
    public final List<String> aLa;
    public final String english;
    public final List<String> aEng;
    public final String type;
    public final int stage;

    public Word(Document entry) {
        english = entry.getString("qEnglish");
        aEng = entry.getList("aEnglish", String.class);
        latin = entry.getString("qLatin");
        aLa = entry.getList("aLatin", String.class);
        type = entry.getString("type");
        stage = entry.getInteger("stage");
    }

    public static List<Word> parse(FindIterable<Document> docs) {
        var words = new ArrayList<Word>();
        for (var d : docs) {
            words.add(new Word(d));
        }
        Collections.shuffle(words);
        return words;
    }

    // getters

    public String getEnglish() {
        return english;
    }

    public List<String> getaEng() {
        return aEng;
    }

    public String getLatin() {
        return latin;
    }

    public List<String> getaLa() {
        return aLa;
    }

    public String getType() {
        return type;
    }

    public int getStage() {
        return stage;
    }
}
