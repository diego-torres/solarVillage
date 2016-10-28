import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SolarVillageForm, SolarVillageFormGroup, SolarVillageFormError, SolarVillageInput, SolarVillageLabel } from './index';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  declarations: [
    SolarVillageForm,
    SolarVillageFormGroup,
    SolarVillageFormError,
    SolarVillageInput,
    SolarVillageLabel
  ],
  exports: [
    SolarVillageForm,
    SolarVillageFormGroup,
    SolarVillageFormError,
    SolarVillageInput,
    SolarVillageLabel
  ]
})
export class SolarVillageFormModule {};
