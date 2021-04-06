/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: by-stage.ts
 * Last Modified: 06/04/2021, 21:18
 */

import {css, customElement, html, LitElement} from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-integer-field.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

@customElement('by-stage')
export class ByStage extends LitElement {
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
      <vaadin-vertical-layout theme="spacing" style="width: 100%; height: 100%;">
        <vaadin-horizontal-layout theme="" id="toggles"
                                  style="align-content: flex-start; flex-shrink: 0; flex-grow: 0; align-self: stretch; flex-wrap: wrap; justify-content: center;"></vaadin-horizontal-layout>
        <vaadin-horizontal-layout theme="spacing-xl"
                                  style="align-items: center; justify-content: center; width: 30%; align-self: center;">
          <vaadin-vertical-layout style="align-items: stretch; flex-shrink: 1; flex-grow: 0;">
            <h5>Selector </h5>
            <vaadin-integer-field id="rgsLBound" has-controls min="1" label="Lower Bound" prevent-invalid-input
                                  has-value></vaadin-integer-field>
            <vaadin-integer-field id="rgsUBound" has-controls min="2" label="Upper Bound" has-value
                                  prevent-invalid-input></vaadin-integer-field>
            <vaadin-button theme="icon" aria-label="Apply" id="rgsApply" style="border-radius: 100px;">
              <iron-icon icon="vaadin:check"></iron-icon>
            </vaadin-button>
          </vaadin-vertical-layout>
          <vaadin-vertical-layout style="align-items: stretch; flex-shrink: 1; flex-grow: 0;">
            <h5>Deselector</h5>
            <vaadin-integer-field id="rgdLBound" has-controls min="1" label="Lower Bound" prevent-invalid-input
                                  has-value></vaadin-integer-field>
            <vaadin-integer-field id="rgdUBound" has-controls min="2" label="Upper Bound" has-value
                                  prevent-invalid-input></vaadin-integer-field>
            <vaadin-button theme="icon" aria-label="Apply" style="border-radius: 100px;" id="rgdApply">
              <iron-icon icon="vaadin:check"></iron-icon>
            </vaadin-button>
          </vaadin-vertical-layout>
          <vaadin-vertical-layout style="align-items: stretch;">
            <vaadin-button id="selAll" style="border-radius: 25px 25px 0px 0px;">
              Select all
            </vaadin-button>
            <vaadin-button id="deselAll" style="border-radius: 0px 0px 25px 25px;">
              Deselect all
            </vaadin-button>
          </vaadin-vertical-layout>
        </vaadin-horizontal-layout>
      </vaadin-vertical-layout>
    `;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
