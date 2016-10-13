import { Component } from '@angular/core';
import { InstallationPermit } from './installation-permit';

const INSTALLATION_PERMITS: InstallationPermit[] = [
  {id: 1,
  beneficiaryName: 'Diego Torres',
  installationAddress: 'Valle Grande Sur #1528',
  electricityContractNumber: '3344-6655-88990',
  buildingDescription: 'A two story building home'},
  {id: 2,
  beneficiaryName: 'Esperanza Romero',
  installationAddress: 'Calle de los lagos 3314',
  electricityContractNumber: '3344-6655-55332',
  buildingDescription: 'simple home, concrete'},
  {id: 3,
  beneficiaryName: 'Jesus Ortega',
  installationAddress: 'Tecamachalco 4825',
  electricityContractNumber: '3344-6655-44332',
  buildingDescription: 'big building close to park'},
  {id: 4,
  beneficiaryName: 'Mario Gomez',
  installationAddress: 'Plaza de Tixtla 808',
  electricityContractNumber: '3344-6655-77684',
  buildingDescription: 'apartments for rent'},
  {id: 5,
  beneficiaryName: 'Jorge Montes',
  installationAddress: 'Ignacio Ramirez 500',
  electricityContractNumber: '3344-6655-34521',
  buildingDescription: 'A two story building home'},
  {id: 6,
  beneficiaryName: 'Daniela Romo',
  installationAddress: 'Francisco I Madero 3350',
  electricityContractNumber: '3344-6655-99873',
  buildingDescription: 'A two story building home'},
  {id: 7,
  beneficiaryName: 'Gabriel Soto',
  installationAddress: 'Alamo 1425',
  electricityContractNumber: '3344-6655-44789',
  buildingDescription: 'A two story building home'},
  {id: 8,
  beneficiaryName: 'Juan Carlos Vazquez',
  installationAddress: 'Ave Henequen 1488',
  electricityContractNumber: '3344-6655-88663',
  buildingDescription: 'A two story building home'},
  {id: 9,
  beneficiaryName: 'Fernando Bravo',
  installationAddress: 'Guajolote 5410',
  electricityContractNumber: '3344-6655-22447',
  buildingDescription: 'A two story building home'},
  {id: 10,
  beneficiaryName: 'Cesar Romero',
  installationAddress: 'Vicente Guerrero 9910',
  electricityContractNumber: '3344-6655-00763',
  buildingDescription: 'A two story building home'}
];

@Component({
  selector: 'solar-village-permits',
  styles: [`
    .selected {
      background-color: #CFD8DC !important;
      color: white;
    }
    .permits {
      margin: 0 0 2em 0;
      list-style-type: none;
      padding: 0;
      width: 15em;
    }
    .permits li {
      cursor: pointer;
      position: relative;
      left: 0;
      background-color: #EEE;
      margin: .5em;
      padding: .3em 0;
      height: 1.6em;
      border-radius: 4px;
    }
    .permits li.selected:hover {
      background-color: #BBD8DC !important;
      color: white;
    }
    .permits li:hover {
      color: #607D8B;
      background-color: #DDD;
      left: .1em;
    }
    .permits .text {
      position: relative;
      top: -3px;
    }
    .permits .badge {
      display: inline-block;
      font-size: small;
      color: white;
      padding: 0.8em 0.7em 0 0.7em;
      background-color: #607D8B;
      line-height: 1em;
      position: relative;
      left: -1px;
      top: -4px;
      height: 1.8em;
      margin-right: .8em;
      border-radius: 4px 0 0 4px;
    }
  `],
  template:`<h1>{{title}}</h1>
    <h2>In progress permit requests</h2>
    <ul class="permits">
      <li *ngFor="let permit of installationPermits" (click)="onSelect(permit)">
        <span class="badge">{{permit.id}}</span> {{permit.beneficiaryName}}
      </li>
    </ul>
    <permit-detail [permit]="selectedPermit"></permit-detail>
    `
})
export class AppComponent {
    title                 = 'Solar village Installation Permits';
    installationPermits = INSTALLATION_PERMITS;
    selectedPermit : InstallationPermit;

    onSelect(permit: InstallationPermit): void {
      this.selectedPermit = permit;
    }
}
