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
        const accessToken = response.headers.get('access-token');

        if (accessToken === null) {
          throw new Error('Token is null');
        }

        this.tokenStorage.setToken('access-token', accessToken);

        const refreshToken = response.headers.get('refresh-token');

        if (refreshToken === null) {
          throw new Error('Token is null');
        }

        this.tokenStorage.setToken('refresh-token', refreshToken);

        this.router.navigate(['dashboard']).then();
      });
  }

  logout(): void {
    this.http
      .post<void>('/api/authentication/logout', {})
      .pipe(take(1))
      .subscribe({
        next: () => {
          this.tokenStorage.removeToken('access-token');
          this.tokenStorage.removeToken('refresh-token');
          this.router.navigate(['']).then();
        },
        error: () => {
          this.tokenStorage.removeToken('access-token');
          this.tokenStorage.removeToken('refresh-token');
          this.router.navigate(['']).then();
        },
      });
  }

  refresh(): void {
    this.postRefresh()
      .pipe(take(1))
      .subscribe((res) => {
        this.tokenStorage.setToken('access-token', res.accessToken);
        this.tokenStorage.setToken('refresh-token', res.refreshToken);
      });
  }

  postRefresh() {
    return this.http.post<{ accessToken: string; refreshToken: string }>(
      '/api/authentication/refresh-token',
      {},
      {
        headers: {
          Authorization:
            'Bearer ' + this.tokenStorage.getToken('refresh-token'),
        },
      }
    );
  }

  isAuthenticated(): boolean {
    const token = this.tokenStorage.getToken('refresh-token');

    if (token === null) {
      return false;
    }

    return !this.jwtHelper.isTokenExpired(token);
  }

  hasRole(role: string): boolean {
    const token = this.tokenStorage.getToken('access-token');

    if (token === null) {
      return false;
    }

    const decodedToken = this.jwtHelper.decodeToken(token);

    return decodedToken['roles'].includes(role);
  }

  getExpiresAt() {
    const token = this.tokenStorage.getToken('refresh-token');

    if (token === null) {
      return false;
    }

    const decodedToken = this.jwtHelper.decodeToken(token);

    return decodedToken['exp'];
  }

  isTokenExpired(token: string) {
    return this.jwtHelper.isTokenExpired(token, 5);
  }
}
