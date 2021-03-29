/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Str.java
 * Last Modified: 29/03/2021, 21:18
 */

package tech.neurobyte.dev.utils;

import java.util.ArrayList;

public class Str {
    public static String join(String delim, ArrayList vals) {
        var build = new StringBuilder();

        for (int i = 0; i < vals.size() - 1; i++) {
            build.append(vals.get(i));
            build.append(delim);
        }
        build.append(vals.get(vals.size() - 1));

        return build.toString();
    }
}
