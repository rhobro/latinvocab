/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Str.java
 * Last Modified: 08/04/2021, 09:35
 */

package tech.neurobyte.dev.utils;

import java.util.*;

public class Str {
    public static Collection<String> splitCollection(String s) {
        var arr = s.split("");
        List<String> c = new ArrayList<>(Collections.emptyList());
        c.addAll(Arrays.asList(arr));
        return c;
    }
}
