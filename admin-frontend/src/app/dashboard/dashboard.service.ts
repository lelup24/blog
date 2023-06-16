import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class DashboardService {
  http = inject(HttpClient);

  public fetchUsers(): Observable<{ msg: string }> {
    return this.http.get<{ msg: string }>('/api/v1/users');
  }

  public fetchUsers2(): Observable<{ msg: string }> {
    return this.http.get<{ msg: string }>('/api/v1/users/2');
  }
}
