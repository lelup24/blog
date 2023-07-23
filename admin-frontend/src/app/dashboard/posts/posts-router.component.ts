import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-posts',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    RouterOutlet,
  ],
  templateUrl: './posts-router.component.html',
  styleUrls: ['./posts-router.component.scss'],
})
export class PostRouter {}
