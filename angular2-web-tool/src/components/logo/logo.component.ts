import { Component } from '@angular/core';

@Component({
  selector: 'sv-logo',
  styles: [require('./logo.css')],
  template: `
    <div classname="flex items-center">
      <img
        class="logo"
        [src]="LogoImage"
        alt="Solar Village"
       />
    </div>
  `
})
export class SolarVillageLogo {
  private LogoImage = require('../../assets/sv-logo.png');
};
