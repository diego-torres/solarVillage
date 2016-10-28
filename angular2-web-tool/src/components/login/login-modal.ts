import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'sv-login-modal',
  template: `
    <sv-modal>
      <sv-modal-content>
        <h1 class='mt0'>Login</h1>
        <sv-login-form
          [isPending]="isPending"
          [hasError]="hasError"
          (onSubmit)="handleSubmit($event)">
        </sv-login-form>
      </sv-modal-content>
    </sv-modal>
  `
})
export class SolarVillageLoginModal{
  @Input() isPending: boolean;
  @Input() hasError: boolean;
  @Output() onSubmit: EventEmitter<Object> = new EventEmitter();

  handleSugmit(login){
    this.onSubmit.emit(login);
  }
};
