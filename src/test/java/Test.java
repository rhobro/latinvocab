/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 30/03/2021, 21:38
 */

import tech.neurobyte.dev.data.Filter;

public class Test {
    public static void main(String[] args) {
        for (var w : Filter.byLetter(true, "sdz")) {
            System.out.println(w.qLa);
        }
    }
}
