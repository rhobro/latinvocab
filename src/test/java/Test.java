/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 11/04/2021, 12:20
 */

import tech.neurobyte.dev.data.Filter;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (var w : Filter.rand(4)) {
                System.out.println(w.qLa);
            }
            System.out.println();
        }
    }
}
