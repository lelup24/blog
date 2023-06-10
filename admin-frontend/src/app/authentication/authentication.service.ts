import { inject, Injectable } from '@angular/core';
import { TokenStorage } from './token-storage.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { take } from 'rxjs';

@Injectable()
export class AuthenticationService {
  tokenStorage = inject(TokenStorage);
  jwtHelper = inject(JwtHelperService);
  http = inject(HttpClient);
  router = inject(Router);

  login(username: string, password: string): void {
    this.http
      .post<void>('/api/login', { username, password }, { observe: 'response' })
      .pipe(take(1))
      .subscribe((response: HttpResponse<void>) => {
        const token = response.headers.get('auth-token');

        if (token === null) {
          throw new Error('Token is null');
        }

        this.tokenStorage.setToken(token);
        this.router.navigate(['dashboard']).then();
      });
  }

  logout(): void {
    this.http
      .post<void>('/api/v1/users/logout', {})
      .pipe(take(1))
      .subscribe({
        next: () => {
          this.tokenStorage.removeToken('auth-token');
          this.router.navigate(['']).then();
        },
        error: () => {
          this.tokenStorage.removeToken('auth-token');
          this.router.navigate(['']).then();
        },
      });
  }

  refresh(): void {
    this.http.post<void>('/api/v1/users/refresh', {}).pipe(take(1)).subscribe();
  }

  isAuthenticated(): boolean {
    const token = this.tokenStorage.getToken();

    if (token === null) {
      return false;
    }

    return !this.jwtHelper.isTokenExpired(token);
  }

  hasRole(role: string): boolean {
    const token = this.tokenStorage.getToken();

    if (token === null) {
      return false;
    }

    const decodedToken = this.jwtHelper.decodeToken(token);

    return decodedToken['roles'].includes(role);
  }

  getExpiresAt() {
    const token = this.tokenStorage.getToken();

    if (token === null) {
      return false;
    }

    const decodedToken = this.jwtHelper.decodeToken(token);

    return decodedToken['exp'];
  }
}
