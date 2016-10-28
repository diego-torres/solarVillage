import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { SolarVillageNavigator, SolarVillageNavigatorItem } from './index';

@NgModule({
  imports : [
    CommonModule,
    ReactiveFormsModule
  ],
  declarations: [
    SolarVillageNavigator,
    SolarVillageNavigatorItem
  ],
  exports: [
    SolarVillageNavigator,
    SolarVillageNavigatorItem
  ]
})
export class SolarVillageNavigatorModule{}
