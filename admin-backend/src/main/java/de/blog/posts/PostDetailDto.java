package de.blog.posts;

import java.util.Objects;
import java.util.UUID;

public class PostDetailDto {

  private UUID id;
  private String title;
  private String content;

  public UUID getId() {
    return id;
  }

  public PostDetailDto setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public PostDetailDto setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public PostDetailDto setContent(String content) {
    this.content = content;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostDetailDto that = (PostDetailDto) o;
    return Objects.equals(id, that.id)
        && Objects.equals(title, that.title)
        && Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, content);
  }
}
