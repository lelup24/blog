import { Injectable } from '@angular/core';

@Injectable()
export class Tokenstorage {
  getToken(): string | null {
    const token = localStorage.getItem('auth-token');

    if (!token) {
      return null;
    }
    return token;
  }

  setToken(token: string): void {
    localStorage.setItem('auth-token', token);
  }
}
