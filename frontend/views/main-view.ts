/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: main-view.ts
 * Last Modified: 07/05/2021, 20:28
 */

import {css, customElement, html, LitElement} from 'lit-element';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-item.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-text-field/src/vaadin-integer-field.js';
import '@vaadin/vaadin-text-field/src/vaadin-number-field.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-select/src/vaadin-select.js';
import './components/uni-footer';

@customElement('main-view')
export class MainView extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
      <vaadin-vertical-layout style="width: 100%; height: 100%; align-items: stretch;" id="root">
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
                                 style="flex-grow: 0; flex-shrink: 1; margin: 0px 10px 0px 10px;">
                    <iron-icon icon="vaadin:arrow-right" id="testDirectionIcon"></iron-icon>
                  </vaadin-button>
                  English
                </vaadin-horizontal-layout>
                <label slot="label">Direction</label>
              </vaadin-form-item>
              <vaadin-form-item>
                <vaadin-select id="type" required></vaadin-select>
                <label slot="label">Type</label>
              </vaadin-form-item>
              <vaadin-form-item>
                <vaadin-integer-field id="nQs" has-controls required min="1" max="1000" prevent-invalid-input step="5"
                                      has-value></vaadin-integer-field>
                <vaadin-button theme="icon" aria-label="Unlimited" id="unlimQs">
                  <iron-icon icon="vaadin:ban" id="unlimQsIcon"></iron-icon>
                </vaadin-button>
                <label slot="label">Number of questions</label>
              </vaadin-form-item>
              <vaadin-form-item>
                <vaadin-number-field id="time" has-controls max="600" step="5" has-value
                                     prevent-invalid-input></vaadin-number-field>
                <vaadin-button theme="icon" aria-label="Add new" id="unlimT">
                  <iron-icon icon="vaadin:ban" id="unlimTIcon"></iron-icon>
                </vaadin-button>
                <label slot="label">Total time (m)</label>
              </vaadin-form-item>
              <vaadin-form-item>
                <vaadin-integer-field id="timePQ" has-controls min="1" max="60" prevent-invalid-input step="5"
                                      has-value></vaadin-integer-field>
                <vaadin-button theme="icon" aria-label="Add new" id="unlimTPQ">
                  <iron-icon icon="vaadin:ban" id="unlimTPQIcon"></iron-icon>
                </vaadin-button>
                <label slot="label">Time per question (s)</label>
              </vaadin-form-item>
              <vaadin-button theme="primary" id="go" style="align-self: center; flex-grow: 0;">
                GO
              </vaadin-button>
            </vaadin-form-layout>
            <vaadin-vertical-layout theme="spacing" id="customizer"
                                    style="align-self: stretch; align-items: stretch;"></vaadin-vertical-layout>
          </vaadin-vertical-layout>
          <label id="wordCount" style="margin: var(--lumo-space-l); align-self: flex-end;">_ words</label>
        </vaadin-vertical-layout>
        <uni-footer></uni-footer>
      </vaadin-vertical-layout>
    `;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
