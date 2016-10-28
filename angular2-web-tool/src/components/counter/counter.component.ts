import { Component, Input, Output, EventEmitter, ChangeDetectionStrategy } from '@angular/core';
import { ICounter } from '../../store';

@Component({
  selector: 'sv-counter',
  template: `
    <div class="flex">
      <sv-button
        className="bg-black col-2"
        (onClick)="decrement.emit()"
        testid="counter-decrementButton">-</sv-button>
    </div>

    <div
      data-testid="counter-result"
      class="flex-auto flex-center center h1">{{ counter.counter }}</div>

    <sv-button
      className="col-2"
      (onClick)="increment.emit()"
      testid="counter-incrementButton">+</sv-button>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SolarVillageCounter {
  @Input() counter: ICounter;
  @Output() increment = new EventEmitter<void>();
  @Output() decrement = new EventEmitter<void>();
}
