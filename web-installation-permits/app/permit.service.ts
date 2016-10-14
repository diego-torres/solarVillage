import { Injectable } from '@angular/core';

import { InstallationPermit } from './installation-permit';
import { INSTALLATION_PERMITS } from './mock-permits';

@Injectable()
export class PermitService {
  getPermits(): Promise<InstallationPermit[]> {
    return Promise.resolve(INSTALLATION_PERMITS);
  }
}
