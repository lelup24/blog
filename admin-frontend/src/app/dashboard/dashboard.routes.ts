import { Routes } from '@angular/router';
import { DashboardRouter } from './dashboard-router.component';

export const DASHBOARD_ROUTES: Routes = [
  {
    path: '',
    component: DashboardRouter,
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./dashboard/dashboard.component').then(
            (c) => c.DashboardComponent
          ),
      },
      {
        path: 'posts',
        loadComponent: () =>
          import('./posts/posts.component').then((c) => c.PostsComponent),
      },
    ],
  },
];
