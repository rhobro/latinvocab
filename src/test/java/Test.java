/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 03/04/2021, 21:18
 */

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.tabs.PagedTabs;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);
        tabs.add("All", new Div(), false);
        tabs.add("By Stage", new H3("stage"), false);
        tabs.add("By Letter", new H3("letter"), false);
        tabs.add("By Type", new H3("type"), false);
    }
}
