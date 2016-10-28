import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { ReactiveFormsModule } from '@angular/forms';

import { SolarVillageForm, SolarVillageLoginModal } from '../index';
import { SolarVillageUiModule } from '../ui/ui.module';
import { SolarVillageModalModule } from '../modal/modal.module';
import { SolarVillageFormModule } from '../form/form.module';

@NgModule({
  imports: [
    ReactiveFormsModule, CommonModule, HttpModule,
    SolarVillageUiModule, SolarVillageModalModule, SolarVillageFormModule ],
  declarations: [
    SolarVillageLoginModal, SolarVillageLoginForm
  ],
  exports: [
    SolarVillageLoginModal
  ]
})
export class SolarVillageLoginModule {}
