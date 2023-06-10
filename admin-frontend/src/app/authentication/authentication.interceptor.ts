import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { TokenStorage } from './token-storage.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  tokenstorage = inject(TokenStorage);

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.tokenstorage.getToken();

    if (!token) {
      return next.handle(req);
    }

    const reqClone = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + token),
    });

    return next.handle(reqClone).pipe(
      map((event) => {
        if (event instanceof HttpResponse) {
          const refreshedToken = event.headers.get('auth-token');

          if (!refreshedToken) {
            return event;
          }

          this.tokenstorage.setToken(refreshedToken);
        }

        return event;
      })
    );
  }
}
