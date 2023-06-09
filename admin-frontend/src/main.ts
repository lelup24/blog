import { importProvidersFrom } from '@angular/core';
import { AppComponent } from './app/app.component';
import { bootstrapApplication, BrowserModule } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { APP_ROUTES } from './app/routes';
import { AuthenticationService } from './app/authentication/authentication.service';
import { Tokenstorage } from './app/authentication/tokenstorage';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { provideAnimations } from '@angular/platform-browser/animations';
import {
  HTTP_INTERCEPTORS,
  HttpClientModule,
  provideHttpClient,
} from '@angular/common/http';
import { AuthenticationInterceptor } from './app/authentication/authentication.interceptor';

export function tokenGetter() {
  return localStorage.getItem('auth-token');
}

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    importProvidersFrom(BrowserModule, HttpClientModule),
    provideRouter(APP_ROUTES),
    { provide: AuthenticationService },
    { provide: Tokenstorage },
    importProvidersFrom(
      JwtModule.forRoot({
        config: {
          tokenGetter: tokenGetter,
        },
      })
    ),
    { provide: JwtHelperService },
    provideAnimations(),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true,
    },
  ],
}).catch((err) => console.error(err));
