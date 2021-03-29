/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 29/03/2021, 20:38
 */

import tech.neurobyte.dev.latin.data.Word;

public class Test {
    public static void main(String[] args) {
        for (var w : Word.list()) {
            System.out.println(w.latin);
        }
    }
}
