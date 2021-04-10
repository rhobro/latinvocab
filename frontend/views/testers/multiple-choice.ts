/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: multiple-choice.ts
 * Last Modified: 10/04/2021, 22:20
 */

import {css, customElement, html, LitElement} from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';

@customElement('multiple-choice')
export class MultipleChoice extends LitElement {
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
      <vaadin-vertical-layout style="width: 100%; height: 100%; padding: var(--lumo-space-xl); align-items: stretch;"
                              id="root" theme="spacing">
        <vaadin-button id="opt1" style="height: 50px;">
          option
        </vaadin-button>
        <vaadin-button style="height: 50px;" id="opt2">
          option
        </vaadin-button>
        <vaadin-button style="height: 50px;" id="opt3">
          option
        </vaadin-button>
        <vaadin-button style="height: 50px;" id="opt4">
          option
        </vaadin-button>
      </vaadin-vertical-layout>
    `;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
