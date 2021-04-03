/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Word.java
 * Last Modified: 03/04/2021, 12:24
 */

package tech.neurobyte.dev.data;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word {
    public final String qLa;
    public final List<String> aLa;
    public final String qEn;
    public final List<String> aEn;
    public final List<String> type;
    public final int stage;

    public Word(Document entry) {
        // set member fields
        qLa = URLDecoder.decode(entry.getString("qLatin"), StandardCharsets.UTF_8); // decode with utf-8
        aLa = entry.getList("aLatin", String.class);
        for (int i = 0; i < aLa.size(); i++) { // decode with utf-8
            aLa.set(i, URLDecoder.decode(aLa.get(i), StandardCharsets.UTF_8));
        }
        qEn = entry.getString("qEnglish");
        aEn = entry.getList("aEnglish", String.class);
        type = entry.getList("type", String.class);
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
