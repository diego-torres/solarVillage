import {NgModule}      from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {
  DevToolsExtension,
  NgRedux
} from 'ng2-redux';
import {NgReduxRouter} from 'ng2-redux-router';
import {
  routing,
  appRoutingProviders
} from './app.routing';
import {
  FormsModule,
  FormBuilder,
  ReactiveFormsModule,
} from '@angular/forms';
import {SolarVillageApp} from './app';
import {SessionActions} from '../actions/session.actions';
import {SessionEpics} from '../epics/session.epics';
import {
  SolarVillageAboutPage,
  SolarVillageCounterPage
} from '../pages';
import {SolarVillageCounter} from '../components/counter/counter.component';
import {SolarVillageLoginModule} from '../components/login/login.module';
import {SolarVillageUiModule} from '../components/ui/ui.module';
import {SolarVillageNavigatorModule} from '../components/navigator/navigator.module';

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    routing,
    CommonModule,
    SolarVillageLoginModule,
    SolarVillageUiModule,
    SolarVillageNavigatorModule
  ],
  declarations: [
    SolarVillageApp,
    SolarVillageAboutPage,
    SolarVillageCounterPage,
    SolarVillageCounter
  ],
  bootstrap: [
    SolarVillageApp
  ],
  providers: [
    DevToolsExtension,
    FormBuilder,
    NgRedux,
    NgReduxRouter,
    appRoutingProviders,
    SessionActions,
    SessionEpics
  ]
})
export class SolarVillageAppModule { }
