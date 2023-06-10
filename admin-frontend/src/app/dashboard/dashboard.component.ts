import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardService } from './dashboard.service';
import { AuthenticationService } from '../authentication/authentication.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatIconModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  providers: [DashboardService],
})
export class DashboardComponent implements OnInit, OnDestroy {
  dashboardService = inject(DashboardService);
  authenticationService = inject(AuthenticationService);
  remainingTimeInSeconds = 0;
  interval!: any;

  ngOnInit(): void {
    this.dashboardService.fetchUsers().subscribe((response) => {
      console.log(response);
    });

    if (this.authenticationService.isAuthenticated()) {
      this.calculateRemainingTime();
    }
  }

  logout(): void {
    this.authenticationService.logout();
  }

  refresh(): void {
    this.authenticationService.refresh();
  }

  private calculateRemainingTime(): void {
    this.interval = setInterval(() => {
      this.executeCalculation();
    }, 1000);
  }

  private executeCalculation() {
    const expiresAt = this.authenticationService.getExpiresAt();

    if (!expiresAt) {
      this.logout();
    }

    this.remainingTimeInSeconds = expiresAt - new Date().getTime() / 1000;

    if (this.remainingTimeInSeconds <= 0) {
      this.logout();
    }
  }

  get minutes(): string {
    return Math.floor(this.remainingTimeInSeconds / 60).toString();
  }

  get seconds(): string {
    return Math.floor(this.remainingTimeInSeconds % 60)
      .toString()
      .padStart(2, '0');
  }

  ngOnDestroy() {
    clearInterval(this.interval);
  }
}
