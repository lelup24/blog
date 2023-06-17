import { inject } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import {
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
} from '@angular/router';

export class AuthenticationGuard {
  authenticationService = inject(AuthenticationService);
  router = inject(Router);

  isAdmin(route: ActivatedRouteSnapshot, _: RouterStateSnapshot): boolean {
    if (!this.authenticationService.isAuthenticated()) {
      this.router.navigate(['']).then();
      return false;
    }

    const requiredRoles: unknown = route.data['roles'];

    if (!(requiredRoles instanceof Array) || requiredRoles.length === 0) {
      return true;
    }

    return requiredRoles.every((role) =>
      this.authenticationService.hasRole(role)
    );
  }
}
