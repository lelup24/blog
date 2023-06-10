import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export type AdminTheme = 'orange' | 'blue';

@Injectable()
export class ThemeService {
  link!: HTMLElement | null;
  activeTheme$: BehaviorSubject<AdminTheme> = new BehaviorSubject<AdminTheme>(
    'orange'
  );

  constructor() {
    this.link = document.getElementById('theme-link');
    this.setTheme(this.activeTheme$.value);
  }

  setTheme(theme: AdminTheme) {
    this.link?.setAttribute('href', `themes/${theme}.css`);
    this.activeTheme$.next(theme);
  }
}
