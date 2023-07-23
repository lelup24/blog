import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  RouterStateSnapshot,
  Routes,
} from '@angular/router';
import { DashboardRouter } from './dashboard-router.component';
import { AuthenticationGuard } from '../authentication/authentication.guard';
import { inject } from '@angular/core';

const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  return inject(AuthenticationGuard).isAuthenticated(route, state);
};
export const DASHBOARD_ROUTES: Routes = [
  {
    path: '',
    providers: [AuthenticationGuard],
    canActivate: [canActivate],
    canActivateChild: [canActivate],
    data: {
      roles: ['ADMIN'],
    },
    component: DashboardRouter,
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./dashboard/dashboard.component').then(
            (c) => c.DashboardComponent
          ),
      },
      {
        path: 'posts',
        loadChildren: () =>
          import('./posts/post.routes').then((c) => c.POST_ROUTES),
      },
    ],
  },
];
