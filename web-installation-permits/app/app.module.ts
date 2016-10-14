import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { RouterModule }   from '@angular/router';

import { AppComponent }           from './app.component';
import { PermitDetailComponent }  from './permit-detail.component';
import { PermitsComponent }       from './permits-component';
import { PermitService }          from './permit.service';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'permits',
        component: PermitsComponent
      }
    ])
  ],
  declarations: [ AppComponent, PermitDetailComponent, PermitsComponent ],
  providers:    [ PermitService ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
