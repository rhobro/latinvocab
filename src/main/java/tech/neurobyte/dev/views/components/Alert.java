/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Alert.java
 * Last Modified: 01/05/2021, 13:32
 */

package tech.neurobyte.dev.views.components;

import com.wontlost.sweetalert2.Config;

public class Alert {

    public static Config errorCancel(String title, String text, String confirm) {
        var cfg = new Config();
        cfg.setTitle(title);
        cfg.setText(text);
        cfg.setBackdrop(true);
        cfg.setIcon("error");
        cfg.setConfirmButtonText(confirm);

        cfg.setAllowEnterKey(false);
        cfg.setAllowEscapeKey(false);
        cfg.setAllowOutsideClick(false);
        return cfg;
    }

    public static Config yesNo(String title, String text, String yes, String no) {
        var cfg = new Config();
        cfg.setTitle(title);
        cfg.setText(text);
        cfg.setBackdrop(true);
        cfg.setIcon("success");
        cfg.setConfirmButtonText(yes);
        cfg.setShowConfirmButton(true);
        cfg.setCancelButtonText(no);
        cfg.setShowCancelButton(true);
        return cfg;
    }
}
