import { Component, ViewEncapsulation } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { Observable } from 'rxjs/Observable';
import { DevToolsExtension, NgRedux, select } from 'ng2-redux';
import { NgReduxRouter } from 'ng2-redux-router';
import { createEpicMiddleware } from 'redux-observable';

import { IAppState, ISession, rootReducer } from '../store';
import { SessionActions } from '../actions/session.actions';
import { SessionEpics } from '../epics/session.epics';
import { SolarVillageAboutPage, SolarVillageCounterPage } from '../pages';
import { middleware, enhancers, reimmutify } from '../store';

import {
  SolarVillageButton,
  SolarVillageNavigator,
  SolarVillageNavigatorItem,
  SolarVillageLogo,
  SolarVillageLoginModal
} from '../components';

import {dev} from '../configuration';

@Component({
  selector: 'solar-village-app',
  // Allow app to define global styles.
  encapsulation: ViewEncapsulation.None,
  styles: [ require('../styles/index.css') ],
  template: require('./app.html')
})
export class SolarVillageApp {
  @select(['session', 'hasError']) hasError$: Observable<boolean>;
  @select(['session', 'isLoading']) isLoading$: Observable<boolean>;
  @select(['session', 'user', 'firstName']) firstName$: Observable<string>;
  @select(['session', 'user', 'lastName']) lastName$: Observable<string>;
  @select(s => !!s.session.token) loggedIn$: Observable<boolean>;
  @select(s => !s.session.token) loggedOut$: Observable<boolean>;

  constructor(
    private devTools: DevToolsExtension,
    private ngRedux: NgRedux<IAppState>,
    private ngReduxRouter: NgReduxRouter,
    private actions: SessionActions,
    private epics: SessionEpics) {

    const enh = (dev && devTools.isEnabled()) ?
      [ ... enhancers, devTools.enhancer({
        deserializeState: reimmutify,
      }) ] :
      enhancers;

    middleware.push(createEpicMiddleware(this.epics.login));

    ngRedux.configureStore(rootReducer, {}, middleware, enhancers);
    ngReduxRouter.initialize();
  }
};
