import { Component, Input } from '@angular/core';
import { InstallationPermit } from './installation-permit';

@Component({
  selector: 'permit-detail',
  template:`
  <div *ngIf="permit">
    <div><label>beneficiary: </label>
      {{permit.beneficiaryName}}</div>
    <div><label>address: </label>
      {{permit.installationAddress}}</div>
    <div><label>building Description : </label>
      {{permit.buildingDescription}}</div>
    <div><label>Electricity Contract: </label>
      {{permit.electricityContractNumber}}</div>
    <div>
      <label>beneficiary: </label>
      <input [(ngModel)]="permit.beneficiaryName"
        placeholder="Beneficiary Name">
    </div>
    <div>
      <label>address: </label>
      <input [(ngModel)]="permit.installationAddress"
        placeholder="Installation address">
    </div>
    <div>
      <label>Electricity contract Number: </label>
      <input [(ngModel)]="permit.buildingDescription"
        placeholder="Electricity contract number">
    </div>
    <div>
      <label>building description: </label>
      <input [(ngModel)]="permit.electricityContractNumber"
        placeholder="Building description">
    </div>
  </div>
  `
})
export class PermitDetailComponent {
  @Input()
  permit: InstallationPermit;
}
