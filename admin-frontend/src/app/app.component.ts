import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NgSwitch, NgSwitchCase, NgSwitchDefault } from '@angular/common';
import { AuthenticationService } from './authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  standalone: true,
  imports: [NgSwitch, NgSwitchDefault, NgSwitchCase, RouterOutlet],
})
export class AppComponent implements OnInit {
  authenticationService = inject(AuthenticationService);
  router = inject(Router);

  ngOnInit(): void {
    if (
      this.authenticationService.isAuthenticated() &&
      this.authenticationService.hasRole('ADMIN')
    ) {
      this.router.navigate(['dashboard']).then();
    }
  }
}
