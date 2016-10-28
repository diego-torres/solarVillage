import { Component, Input } from '@angular/core';

@Component({
  selector: 'sv-label',
  template: `
    <label [id]="qaid">
      <ng-content></ng-content>
    </label>
  `
})
export class SolarVillageLabel {
  @Input() qaid: string;
};
