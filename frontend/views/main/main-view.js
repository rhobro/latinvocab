import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';

/**
 * `main-view`
 *
 * MainView element.
 *
 * @customElement
 * @polymer
 */
class MainView extends PolymerElement {

    static get template() {
        return html`
            <style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
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
