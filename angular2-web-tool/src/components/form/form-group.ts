import { Component, Input } from '@angular/core';

@Component({
  selector: 'sv-form-group',
  template: `
    <div
      [attr.data-testid]="testid"
      class="py2">
      <ng-content></ng-content>
    </div>
  `
})
export class SolarVillageFormGroup {
  @Input() testid: string;
};
