import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, filter, switchMap, take } from 'rxjs/operators';
import { TokenStorage } from './token-storage.service';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  private tokenStorage = inject(TokenStorage);
  private authService = inject(AuthenticationService);
  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(
    null
  );

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const accessToken = this.tokenStorage.getToken('access-token');

    if (
      !accessToken ||
      req.url.includes('/api/authentication/refresh-token') ||
      req.url.includes('/api/authentication/logout') ||
      req.url.includes('/api/login')
    ) {
      return next.handle(req);
    }

    if (this.authService.isTokenExpired(accessToken)) {
      if (!this.isRefreshing) {
        this.isRefreshing = true;
        this.refreshTokenSubject.next(null);

        return this.authService.postRefresh().pipe(
          switchMap((res) => {
            this.tokenStorage.setToken('access-token', res.accessToken);
            this.tokenStorage.setToken('refresh-token', res.refreshToken);
            this.isRefreshing = false;
            this.refreshTokenSubject.next(res.accessToken);

            return this.retryFailedRequests(req, next);
          }),
          catchError((err) => {
            this.authService.logout();
            this.isRefreshing = false;
            return throwError(err);
          })
        );
      } else {
        return this.retryFailedRequests(req, next);
      }
    }

    const reqClone = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + accessToken),
    });

    return next.handle(reqClone);
  }

  private retryFailedRequests(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return this.refreshTokenSubject.pipe(
      filter((token) => token !== null),
      take(1),
      switchMap((token) => {
        const reqClone = req.clone({
          headers: req.headers.set('Authorization', 'Bearer ' + token),
        });

        return next.handle(reqClone);
      })
    );
  }
}
