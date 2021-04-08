/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: by-type.ts
 * Last Modified: 08/04/2021, 12:20
 */

import {css, customElement, html, LitElement} from 'lit-element';

@customElement('by-type')
export class ByType extends LitElement {
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
      </vaadin-vertical-layout>
    `;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
