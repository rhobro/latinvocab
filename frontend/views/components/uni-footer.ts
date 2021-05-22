/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: uni-footer.ts
 * Last Modified: 22/05/2021, 21:59
 */

import {css, customElement, html, LitElement} from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

@customElement('uni-footer')
export class UniFooter extends LitElement {
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
      <vaadin-horizontal-layout class="footer"
                                style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct); padding: var(--lumo-space-m); justify-content: center; align-items: center;">
        <h6 id="cpyr">Copyright © Rohan Mathew. CLC ex-student. All rights reserved.</h6>
      </vaadin-horizontal-layout>
    `;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
