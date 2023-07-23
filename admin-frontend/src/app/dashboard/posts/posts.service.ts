import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PostListItem {
  id: string;
  title: string;
}

export interface PostDetail {
  id: string;
  title: string;
  content: string;
}

@Injectable()
export class PostsService {
  private http: HttpClient = inject(HttpClient);

  public fetchPosts(): Observable<PostListItem[]> {
    return this.http.get<PostListItem[]>('/api/v1/posts');
  }

  public fetchPost(id: string): Observable<PostDetail> {
    return this.http.get<PostDetail>(`/api/v1/posts/${id}`);
  }
}
