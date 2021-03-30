/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: DB.java
 * Last Modified: 30/03/2021, 21:38
 */

package tech.neurobyte.dev.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DB {
    private static final MongoClient c;
    public static MongoCollection<Document> words;

    static {
        c = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = c.getDatabase("latinvocab");
        words = db.getCollection("words");
    }

    public static void close() {
        c.close();
    }
}
