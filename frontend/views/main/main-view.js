/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: main-view.js
 * Last Modified: 02/04/2021, 08:12
 */

import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-item.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-column.js';

class MainView extends PolymerElement {

    static get template() {
        return html`
            <style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
            <vaadin-vertical-layout style="width: 100%; height: 100%;">
                <vaadin-horizontal-layout class="header"
                                          style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct);"></vaadin-horizontal-layout>
                <vaadin-vertical-layout class="content"
                                        style="width: 100%; flex-grow: 1; flex-shrink: 1; flex-basis: auto; align-items: stretch;"
                                        id="body">
                    <vaadin-vertical-layout theme="spacing"
                                            style="margin: var(--lumo-space-xl); padding: var(--lumo-space-s); flex-direction: column;">
                        <vaadin-form-layout>
                            <vaadin-form-item></vaadin-form-item>
                        </vaadin-form-layout>
                        <vaadin-vertical-layout theme="spacing" id="customizer"
                                                style="align-self: stretch; align-items: stretch;"></vaadin-vertical-layout>
                    </vaadin-vertical-layout>
                    <vaadin-grid id="wordGrid" style="align-self: stretch;" multi-sort loading
                                 column-reordering-allowed>
                        <vaadin-grid-column resizable header="Latin"></vaadin-grid-column>
                        <vaadin-grid-column header="English"></vaadin-grid-column>
                        <vaadin-grid-column flex-grow="0" header="Type"></vaadin-grid-column>
                        <vaadin-grid-column flex-grow="0" header="Stage"></vaadin-grid-column>
                    </vaadin-grid>
                </vaadin-vertical-layout>
                <vaadin-horizontal-layout class="footer"
                                          style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct); justify-content: center;">
                    <h6 id="cpyr">Copyright</h6>
                </vaadin-horizontal-layout>
            </vaadin-vertical-layout>
        `;
    }

    static get is() {
        return 'main-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(MainView.is, MainView);
