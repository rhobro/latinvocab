/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 06/04/2021, 19:18
 */

import tech.neurobyte.dev.data.Filter;

import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        for (var w : Filter.byStage(Arrays.asList(1, 3, 5))) {
            System.out.printf("%s - %d\n", w.qLa, w.stage);
        }
    }
}
