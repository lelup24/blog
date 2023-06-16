import { inject } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import {
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { Roles } from './types';

export class AuthenticationGuard {
  authenticationService = inject(AuthenticationService);
  router = inject(Router);

  isAdmin(route: ActivatedRouteSnapshot, _: RouterStateSnapshot): boolean {
    if (!this.authenticationService.isAuthenticated()) {
      this.router.navigate(['']).then();
      return false;
    }

    const roles: Roles[] = route.data['roles'];

    return roles.every((role) => this.authenticationService.hasRole(role));
  }
}
