import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  RouterStateSnapshot,
  Routes,
} from '@angular/router';
import { inject } from '@angular/core';
import { AuthenticationGuard } from '../../authentication/authentication.guard';
import { PostsService } from './posts.service';

const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  return inject(AuthenticationGuard).isAuthenticated(route, state);
};
export const POST_ROUTES: Routes = [
  {
    path: '',
    providers: [AuthenticationGuard, PostsService],
    canActivate: [canActivate],
    canActivateChild: [canActivate],
    data: {
      roles: ['ADMIN'],
    },
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./post-list/post-list.component').then(
            (c) => c.PostListComponent
          ),
      },
      {
        path: ':id',
        loadComponent: () =>
          import('./post-detail/post-detail.component').then(
            (c) => c.PostDetailComponent
          ),
      },
    ],
  },
];
