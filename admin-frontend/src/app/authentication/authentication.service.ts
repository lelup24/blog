import { inject, Injectable } from '@angular/core';
import { Tokenstorage } from './tokenstorage';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class AuthenticationService {
  tokenstorage = inject(Tokenstorage);
  jwtHelper = inject(JwtHelperService);
  http = inject(HttpClient);
  router = inject(Router);

  login(username: string, password: string): void {
    this.http
      .post<void>('/api/login', { username, password }, { observe: 'response' })
      .subscribe((response: HttpResponse<void>) => {
        const token = response.headers.get('auth-token');

        if (token === null) {
          throw new Error('Token is null');
        }

        this.tokenstorage.setToken(token);
        this.router.navigate(['dashboard']).then();
      });
  }

  isAuthenticated(): boolean {
    const token = this.tokenstorage.getToken();

    if (token === null) {
      return false;
    }

    return !this.jwtHelper.isTokenExpired(token);
  }

  hasRole(role: string): boolean {
    const token = this.tokenstorage.getToken();

    if (token === null) {
      return false;
    }

    const decodedToken = this.jwtHelper.decodeToken(token);

    return decodedToken['roles'].includes(role);
  }
}
