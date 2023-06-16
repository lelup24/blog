import { Injectable } from '@angular/core';

export type TokenKey = 'access-token' | 'refresh-token';

@Injectable()
export class TokenStorage {
  getToken(key: TokenKey): string | null {
    return localStorage.getItem(key);
  }

  setToken(key: TokenKey, token: string): void {
    localStorage.setItem(key, token);
  }

  removeToken(key: TokenKey) {
    localStorage.removeItem(key);
  }
}
