// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
<style include='lumo-badge'>
        html {
      --lumo-border-radius: calc(var(--lumo-size-m) / 2);

    }

</style>
</custom-style>

<dom-module id="theme-vaadin-text-field-0" theme-for="vaadin-text-field">
    <template>
        <style>
        
[part="input-field"] {
  box-shadow: inset 0 0 0 1px var(--lumo-contrast-30pct);
  background-color: var(--lumo-base-color);
}

:host([focus-ring]) [part="input-field"] {
  box-shadow: 0 0 0 2px var(--lumo-primary-color-50pct), inset 0 0 0 1px var(--lumo-primary-color);
}

:host([invalid]) [part="input-field"] {
  box-shadow: inset 0 0 0 1px var(--lumo-error-color);
}

        </style>
    </template>
</dom-module>
<dom-module id="theme-vaadin-text-area-0" theme-for="vaadin-text-area">
    <template>
        <style>
        
[part="input-field"] {
  box-shadow: inset 0 0 0 1px var(--lumo-contrast-30pct);
  background-color: var(--lumo-base-color);
}

:host([focus-ring]) [part="input-field"] {
  box-shadow: 0 0 0 2px var(--lumo-primary-color-50pct), inset 0 0 0 1px var(--lumo-primary-color);
}

:host([invalid]) [part="input-field"] {
  box-shadow: inset 0 0 0 1px var(--lumo-error-color);
}

        </style>
    </template>
</dom-module>
<dom-module id="theme-vaadin-password-field-0" theme-for="vaadin-password-field">
    <template>
        <style>
        
[part="input-field"] {
  box-shadow: inset 0 0 0 1px var(--lumo-contrast-30pct);
  background-color: var(--lumo-base-color);
}

:host([focus-ring]) [part="input-field"] {
  box-shadow: 0 0 0 2px var(--lumo-primary-color-50pct), inset 0 0 0 1px var(--lumo-primary-color);
}

:host([invalid]) [part="input-field"] {
  box-shadow: inset 0 0 0 1px var(--lumo-error-color);
}

        </style>
    </template>
</dom-module>

`;

document.head.appendChild($_documentContainer.content);
