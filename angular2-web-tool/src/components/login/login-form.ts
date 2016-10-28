import { Component, EventEmitter, Input, Output } from '@angular/core';
import{ FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'sv-login-form',
  template: `
    <sv-form
      [group]="group"
      (onSubmit)="handleSubmit()">
      <sv-alert
        qaid="qa-pending"
        testid="alert-pending"
        status='info'
        *ngIf="isPending">Loading...</sv-alert>
      <sv-alert
        qaid="qa-alert"
        testid="alert-error"
        status='error'
        *ngIf="hasError">Invalid username and password</sv-alert>


      <sv-form-group
        testid="login-username">
        <sv-label qaid="qa-uname-label">Username</sv-label>
        <sv-input
          qaid="qa-uname-input"
          inputType='text'
          placeholder='Username'
          [control]="username"></sv-input>
        <sv-form-error
          qaid="qa-uname-validation"
          [visible]="showNameWarning()">Username is required</sv-form-error>
      </sv-form-group>

      <sv-form-group
        testid="login-password">
        <sv-label qaid="qa-password-label">Password</sv-label>
        <sv-input
          qaid="qa-password-input"
          inputType='text'
          placeholder='Password'
          [control]="password"></sv-input>
        <sv-form-error
          qaid="qa-password-validation"
          [visible]="showPasswordWarning()">Password is required</sv-form-error>
      </sv-form-group>

      <sv-form-group
        testid="login-submit">
        <sv-button
          qaid="qa-login-button"
          className="mr1"
          type="submit">Login</sv-button>
        <sv-button
            qaid="qa-clear-button"
            className="bg-red"
            type="reset"
            (onClick)="reset()">Clear</sv-button>
      </sv-form-group>
    </sv-form>
  `
})
export class SolarVillageLoginForm {
  @Input isPending: boolean;
  @Input hasError: boolean;
  @Output onSubmit: EventEmitter<Object> = new EventEmitter();

  username: FormControl;
  password: FormControl;
  group: FormGroup;

  constructor(private builder: FormBuilder){
    this.reset();
  }

  showNameWarning() {
    return this.username.touched && !this.username.valid && this.username.hasError('required');
  }

  showPasswordWarning() {
    return this.password.touched && !this.password.valid && this.password.hasError('required');
  }

  handleSubmit() {
    this.password.markAsTouched();
    this.username.markAsTouched();

    if (this.password.value && this.username.value) {
      this.onSubmit.emit(this.group.value);
    }
  }

  reset() {
    this.username = new FormControl('', Validators.required);
    this.password = new FormControl('', Validators.required);
    this.hasError = false;
    this.isPending = false;
    this.group = this.builder.group({
      username: this.username,
      password: this.password
    });

  }
};
