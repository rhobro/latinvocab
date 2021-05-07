/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: UniFooter.java
 * Last Modified: 07/05/2021, 20:28
 */

package tech.neurobyte.dev.views.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;

import java.time.LocalDate;

@Tag("uni-footer")
@JsModule("./views/components/uni-footer.ts")
public class UniFooter extends LitTemplate {

    @Id("cpyr")
    private H6 cpyr;

    public UniFooter() {
        // TODO not running when in other designs but running with route tag
        cpyr.setText(String.format("Copyright © %d Rohan Mathew. All rights reserved.", LocalDate.now().getYear()));
    }

}
