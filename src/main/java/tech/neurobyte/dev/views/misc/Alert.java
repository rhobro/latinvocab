/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Alert.java
 * Last Modified: 10/04/2021, 19:55
 */

package tech.neurobyte.dev.views.misc;

import com.wontlost.sweetalert2.Config;

public class Alert {

    public static Config errorCancel(String title, String text, String confirm) {
        var cfg = new Config();
        cfg.setTitle(title);
        cfg.setText(text);
        cfg.setBackdrop(true);
        cfg.setTimer(10000L);
        cfg.setIcon("error");
        cfg.setConfirmButtonText(confirm);
        return cfg;
    }
}
