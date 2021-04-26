/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: UniversalFooter.java
 * Last Modified: 25/04/2021, 22:06
 */

package tech.neurobyte.dev.views.misc;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("footer")
@Tag("universal-footer")
@JsModule("./views/misc/universal-footer.ts")
public class UniversalFooter extends LitTemplate {

    @Id("cpyr")
    private H6 cpyr;

    public UniversalFooter() {
        cpyr.setText(String.format("Copyright © %d Rohan Mathew. All rights reserved.", LocalDate.now().getYear()));
    }

}
