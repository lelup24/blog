import { Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';

export const APP_ROUTES: Routes = [
  {
    path: '',
    component: LoginPageComponent,
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./dashboard/dashboard.routes').then((r) => r.DASHBOARD_ROUTES),
  },
  { path: '**', redirectTo: '' },
];
