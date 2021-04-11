/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: test-view.ts
 * Last Modified: 11/04/2021, 13:32
 */

import {css, customElement, html, LitElement} from 'lit-element';
import './misc/universal-footer';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

@customElement('test-view')
export class TestView extends LitElement {
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
      <vaadin-vertical-layout style="width: 100%; height: 100%; align-items: stretch;">
        <vaadin-horizontal-layout class="header"
                                  style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 1; background-color: var(--lumo-contrast-10pct);"></vaadin-horizontal-layout>
        <vaadin-vertical-layout class="content"
                                style="padding: var(--lumo-space-xl); flex-grow: 1; flex-shrink: 0; align-items: center;"
                                theme="spacing-xl">
          <vaadin-vertical-layout theme="spacing-xs" style="align-self: stretch; align-items: center;">
            <h1 id="word">word</h1>
            <h3 id="gramType">type</h3>
          </vaadin-vertical-layout>
          <vaadin-vertical-layout theme="spacing" id="tester"
                                  style="flex-shrink: 0; flex-grow: 1; align-items: stretch; width: 80%;"></vaadin-vertical-layout>
        </vaadin-vertical-layout>
        <universal-footer style="flex-shrink: 1;"></universal-footer>
      </vaadin-vertical-layout>
    `;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
