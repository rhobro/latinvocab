/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 08/04/2021, 12:20
 */

import tech.neurobyte.dev.data.DB;
import tech.neurobyte.dev.data.Word;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.in;

public class Test {
    public static void main(String[] args) {
        var filtrate = DB.words.find(in("type", Arrays.asList("adjective", "verb")));
        var f = Word.parse(filtrate);

        for (var t : f) {
            System.out.println(t.qLa + " " + t.type);
        }
    }
}
