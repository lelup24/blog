import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { PostListItem, PostsService } from '../posts.service';
import { RouterLink } from '@angular/router';
import { take } from 'rxjs';

@Component({
  selector: 'app-post-list',
  standalone: true,
  imports: [CommonModule, MatCardModule, RouterLink],
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss'],
})
export class PostListComponent implements OnInit {
  private postsService: PostsService = inject(PostsService);

  posts!: PostListItem[];

  ngOnInit(): void {
    this.postsService
      .fetchPosts()
      .pipe(take(1))
      .subscribe((posts) => {
        this.posts = posts;
      });
  }
}
