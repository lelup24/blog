import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable, take } from 'rxjs';
import { PostDetail, PostsService } from '../posts.service';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-post-detail',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss'],
})
export class PostDetailComponent implements OnInit {
  route: ActivatedRoute = inject(ActivatedRoute);
  postsService = inject(PostsService);

  postDetail!: Observable<PostDetail>;

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      if (!params.has('id')) {
        return;
      }

      this.postDetail = this.postsService
        .fetchPost(params.get('id')!)
        .pipe(take(1));
    });
  }
}
