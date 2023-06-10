import { Injectable } from '@angular/core';

@Injectable()
export class TokenStorage {
  getToken(): string | null {
    return localStorage.getItem('auth-token');
  }

  setToken(token: string): void {
    localStorage.setItem('auth-token', token);
  }

  removeToken(key: string) {
    localStorage.removeItem(key);
  }
}
