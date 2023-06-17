import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-post-detail',
  changeDetection: ChangeDetectionStrategy.OnPush,
  standalone: true,
  imports: [CommonModule, MatInputModule, FormsModule],
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.scss'],
})
export class PostCreateComponent {
  sanitizer = inject(DomSanitizer);

  post: string = `
    <div style="display: flex; flex-direction: column; align-items: center;">
      <h1 style="font-size: 2rem; margin-bottom: 1rem;">Post Title</h1>
      <div style="display: flex; width: 100%; flex-direction: column; align-items: flex-start;">
        <p style="font-size: 1rem; margin-bottom: 1rem;">Post Content</p>
      </div>
    </div>
  `;

  get previewPost() {
    return this.sanitizer.bypassSecurityTrustHtml(this.post);
  }
}
