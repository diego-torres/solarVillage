import { Component, OnInit } from '@angular/core';

import { InstallationPermit } from './installation-permit';
import { PermitService } from './permit.service';

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
    `,
  providers: [PermitService]
})
export class AppComponent {
    title                 = 'Solar village Installation Permits';
    installationPermits: InstallationPermit[];
    selectedPermit: InstallationPermit;

    constructor(private permitService: PermitService) { }

    getPermits(): void {
      this.permitService.getPermits().then(
        installationPermits => this.installationPermits = installationPermits
      );
    }

    ngOnInit(): void{
      this.getPermits();
    }

    onSelect(permit: InstallationPermit): void {
      this.selectedPermit = permit;
    }
}
