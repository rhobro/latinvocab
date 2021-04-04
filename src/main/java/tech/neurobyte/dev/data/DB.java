/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: DB.java
 * Last Modified: 04/04/2021, 18:02
 */

package tech.neurobyte.dev.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import org.bson.Document;

import java.util.Collections;
import java.util.Objects;

import static com.mongodb.client.model.Aggregates.group;

public class DB {
    private static final MongoClient c;
    public static MongoCollection<Document> words;

    static {
        c = MongoClients.create("mongodb://root:eTDgVA5YqWayjTECpmiQdNYkgzkphg63usIswrHX@localhost:27017");
        MongoDatabase db = c.getDatabase("latinvocab");
        words = db.getCollection("words");
    }

    public static int getNStages() {
        return Objects.requireNonNull(DB.words.aggregate(Collections.singletonList(
                group("", Accumulators.max("max", "$stage"))
        )).first()).getInteger("max");
    }

    public static void close() {
        c.close();
    }
}
