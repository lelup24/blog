import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  RouterStateSnapshot,
  Routes,
} from '@angular/router';
import { inject } from '@angular/core';
import { AuthenticationGuard } from './authentication/authentication.guard';

const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  return inject(AuthenticationGuard).isAdmin(route, state);
};

export const APP_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./login-page/login-page.component').then(
        (c) => c.LoginPageComponent
      ),
  },
  {
    path: 'dashboard',
    providers: [AuthenticationGuard],
    canActivate: [canActivate],
    canActivateChild: [canActivate],
    data: {
      roles: ['ADMIN'],
    },
    loadComponent: () =>
      import('./dashboard/dashboard.component').then(
        (c) => c.DashboardComponent
      ),
  },
];
