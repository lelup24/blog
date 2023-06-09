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

  isAdmin(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (!this.authenticationService.isAuthenticated()) {
      this.router.navigate(['']).then();
      return false;
    }

    const roles: string[] = route.data['roles'];

    for (const role of roles) {
      if (this.authenticationService.hasRole(role)) {
        return true;
      }
    }

    return false;
  }
}
