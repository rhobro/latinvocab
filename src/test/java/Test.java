/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: Test.java
 * Last Modified: 02/04/2021, 22:27
 */

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println(new Icon(VaadinIcon.ARROW_RIGHT).getElement().getAttribute("icon"));
    }
}
