import { Component } from '@angular/core';

@Component({
  selector: 'solar-village-app',
  template:`<h1>{{title}}</h1>
    <solar-village-permits></solar-village-permits>`
})
export class AppComponent {
    title                 = 'Solar village Installation Permits';
}
