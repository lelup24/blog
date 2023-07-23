package de.blog.posts;

import java.util.Objects;
import java.util.UUID;

public class PostListDto {

  private UUID id;
  private String title;

  public UUID getId() {
    return id;
  }

  public PostListDto setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public PostListDto setTitle(String title) {
    this.title = title;
    return this;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostListDto that = (PostListDto) o;
    return Objects.equals(id, that.id)
        && Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title);
  }
}
