import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SolarVillageModal } from './modal.component';
import { SolarVillageModalContent } from './modal-content.component';

@NgModule({
  imports: [ CommonModule ],
  declarations: [ SolarVillageModal, SolarVillageModalContent ],
  exports: [ SolarVillageModal, SolarVillageModalContent ]
})
export class SolarVillageModalModule {}
