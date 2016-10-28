import { Component, Input } from '@angular/core';

@Component({
  selector: 'sv-alert',
  template: `
    <div
      [id]="qaid"
      class="p2 bold"
      [attr.data-testid]="testid"
      [ngClass]="{
        'bg-blue': status === 'info',
        'bg-yellow': status === 'warning',
        'bg-green': status === 'success',
        'bg-red': status === 'error',
        'white': status === 'info' || status === 'error'
      }">
      <ng-content></ng-content>
    </div>
  `
})
export class SolarVillageAlert {
  @Input() status = 'info';
  @Input() qaid: string;
  @Input() testid: string;
};
