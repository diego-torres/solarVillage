import { Component, OnInit } from '@angular/core';

import { InstallationPermit } from './installation-permit';
import { PermitService } from './permit.service';

@Component({
  selector: 'solar-village-permits',
  template:`
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
export class PermitsComponent implements OnInit {
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
