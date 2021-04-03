/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: main-view.js
 * Last Modified: 03/04/2021, 21:18
 */

import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-item.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-text-field/src/vaadin-integer-field.js';
import '@vaadin/vaadin-text-field/src/vaadin-number-field.js';

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
                            <vaadin-form-item>
                                <vaadin-horizontal-layout style="flex-direction: row; align-items: center;">
                                    Latin
                                    <vaadin-button theme="icon" aria-label="direction" id="testDirection"
                                                   style="flex-grow: 0; flex-shrink: 1; margin: 0px 10px 0px 10px;"></vaadin-button>
                                    English
                                </vaadin-horizontal-layout>
                                <label slot="label">Direction</label>
                            </vaadin-form-item>
                            <vaadin-form-item>
                                <label slot="label">Number of questions</label>
                                <vaadin-integer-field id="nQs" has-controls required min="1" value="10" max="1000"
                                                      prevent-invalid-input step="5" has-value></vaadin-integer-field>
                                <vaadin-button theme="icon" aria-label="Unlimited" id="unlimQs"></vaadin-button>
                            </vaadin-form-item>
                            <vaadin-form-item>
                                <vaadin-number-field id="time" has-controls max="600" step="5"
                                                     has-value></vaadin-number-field>
                                <vaadin-button theme="icon" aria-label="Add new" id="unlimT"></vaadin-button>
                                <label slot="label">Total time (m)</label>
                            </vaadin-form-item>
                            <vaadin-form-item>
                                <vaadin-integer-field id="timePQ" has-controls required min="1" max="60"
                                                      prevent-invalid-input step="5" has-value></vaadin-integer-field>
                                <vaadin-button theme="icon" aria-label="Add new" id="unlimTPQ"></vaadin-button>
                                <label slot="label">Time per question (s)</label>
                            </vaadin-form-item>
                        </vaadin-form-layout>
                        <vaadin-vertical-layout theme="spacing" id="customizer"
                                                style="align-self: stretch; align-items: stretch;"></vaadin-vertical-layout>
                    </vaadin-vertical-layout>
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
