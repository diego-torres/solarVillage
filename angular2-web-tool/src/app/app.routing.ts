import { Routes, RouterModule } from '@angular/router';
import { SOLAR_VILLAGE_APP_ROUTES } from './app.routes';

const appRoutes: Routes = SOLAR_VILLAGE_APP_ROUTES;

export const appRoutingProviders: any[] = [];
export const routing = RouterModule.forRoot(appRoutes);
