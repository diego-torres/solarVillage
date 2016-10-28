import { Routes } from '@angular/router';

import {
  SolarVillageCounterPage,
  SolarVillageAboutPage
} from '../pages';

export const SOLAR_VILLAGE_APP_ROUTES: Routes = [{
  pathMatch: 'full',
  path: '',
  redirectTo: 'counter'
}, {
  path: 'counter',
  component: SolarVillageCounterPage
}, {
  path: 'about',
  component: SolarVillageAboutPage
}];
