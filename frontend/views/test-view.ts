/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: test-view.ts
 * Last Modified: 22/05/2021, 21:59
 */

import {css, customElement, html, LitElement} from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/flow-frontend/simple-timer/simple-timer.js';
import './components/uni-footer';

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
        <vaadin-horizontal-layout id="header" class="header"
                                  style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 1; background-color: var(--lumo-contrast-10pct); align-items: center; justify-content: space-between; padding: var(--lumo-space-xs);">
          <vaadin-vertical-layout theme="spacing" style="align-items: flex-start; flex-shrink: 0; flex-grow: 1;">
            <vaadin-button theme="icon" id="pause">
              <iron-icon icon="vaadin:pause" id="pauseIcon"></iron-icon>
            </vaadin-button>
          </vaadin-vertical-layout>
          <vaadin-vertical-layout theme="spacing" style="align-items: flex-end; flex-shrink: 0; flex-grow: 1;">
            <simple-timer id="total" hours start-time="" minutes visible="" current-time="0"></simple-timer>
            <simple-timer id="tpq" hours start-time="" minutes current-time="0"></simple-timer>
          </vaadin-vertical-layout>
        </vaadin-horizontal-layout>
        <vaadin-vertical-layout class="content"
                                style="flex-grow: 1; flex-shrink: 0; align-items: center; padding: var(--lumo-space-xs);">
          <vaadin-vertical-layout style="align-self: stretch; align-items: center;">
            <h1 id="word">word</h1>
            <h3 id="gramType">type</h3>
          </vaadin-vertical-layout>
          <vaadin-vertical-layout id="tester"
                                  style="flex-shrink: 0; flex-grow: 1; align-items: stretch;"></vaadin-vertical-layout>
          <vaadin-horizontal-layout theme="spacing-xl" style="align-items: center;">
            <label id="score">score</label>
            <vaadin-button theme="icon" id="nextQ">
              <iron-icon icon="vaadin:arrow-right"></iron-icon>
            </vaadin-button>
          </vaadin-horizontal-layout>
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
