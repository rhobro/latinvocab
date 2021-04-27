/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Data.java
 * Last Modified: 26/04/2021, 22:36
 */

package tech.neurobyte.dev.data;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Data {
    private static final String JS_VOCAB_LIST = "https://www.exams.cambridgescp.com/files/cscp/wjec18vocab/vt.js";

    public static List<Word> words = new ArrayList<>();

    static {
        var cli = HttpClient.newHttpClient();
        var rq = HttpRequest.newBuilder()
                .uri(URI.create(JS_VOCAB_LIST))
                .build();
        HttpResponse<String> rsp = null;
        try {
            rsp = cli.send(rq, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // loop through each raw word string
        assert rsp != null;
        for (var line : rsp.body().split("\n")) {
            if (line.contains("\"")) {
                // parse each part of string
                line = line.substring(line.indexOf("\"") + 1);
                line = line.substring(0, line.indexOf("\""));
                words.add(new Word(line));
            }
        }
    }

    public static int getNStages() {
        var stages = new HashSet<Integer>();
        for (var w : words) {
            stages.add(w.stage);
        }
        return stages.size();
    }

    public static Set<String> getTypes() {
        var types = new HashSet<String>();
        for (var w : words) {
            types.addAll(w.type);
        }
        return types;
    }
}
