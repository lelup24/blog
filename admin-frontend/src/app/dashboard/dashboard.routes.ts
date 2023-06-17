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
  return inject(AuthenticationGuard).isAdmin(route, state);
};
export const DASHBOARD_ROUTES: Routes = [
  {
    path: '',
    // providers: [AuthenticationGuard],
    // canActivate: [canActivate],
    // canActivateChild: [canActivate],
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
        // data: {
        //   roles: ['ADMIN'],
        // },
        path: 'posts',
        loadComponent: () =>
          import('./posts/posts.component').then((c) => c.PostsComponent),
      },
      {
        // data: {
        //   roles: ['ADMIN'],
        // },
        path: 'posts/new',
        loadComponent: () =>
          import('./posts/post-create/post-create.component').then(
            (c) => c.PostCreateComponent
          ),
      },
    ],
  },
];
