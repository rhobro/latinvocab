/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 01/04/2021, 21:02
 */

import tech.neurobyte.dev.data.Filter;

public class Test {
    public static void main(String[] args) {
        for (var w : Filter.byLetter(true, "s")) {
            System.out.println(w.latin);
        }
    }
}
