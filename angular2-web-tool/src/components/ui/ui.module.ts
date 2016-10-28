import {NgModule}      from '@angular/core';
import {CommonModule} from '@angular/common';
import {SolarVillageAlert} from '../alert/alert.component';
import {SolarVillageButton} from '../button/button.component';
import {SolarVillageLogo} from '../logo/logo.component';
import {SolarVillageContainer} from '../container/container.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    SolarVillageAlert,
    SolarVillageButton,
    SolarVillageLogo,
    SolarVillageContainer
  ],
  exports: [
    SolarVillageAlert,
    SolarVillageButton,
    SolarVillageLogo,
    SolarVillageContainer
  ]
})
export class SolarVillageUiModule { }
