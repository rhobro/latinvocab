/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 26/04/2021, 22:34
 */

import tech.neurobyte.dev.data.Filter;

public class Test {
    public static void main(String[] args) {
        Filter.all().forEach(e -> System.out.println(e.qLa));
    }
}
