package de.blog.posts;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {

  @GetMapping
  public ResponseEntity<List<PostListDto>> getPosts() {
    return ResponseEntity.ok(
        List.of(
            new PostListDto()
                .setId(UUID.randomUUID())
                .setTitle("Hello World"),
            new PostListDto()
                .setId(UUID.randomUUID())
                .setTitle("Hello World 2")));
  }

  @GetMapping("{id}")
    public ResponseEntity<PostDetailDto> getPost() {
        return ResponseEntity.ok(
            new PostDetailDto()
                .setId(UUID.randomUUID())
                .setTitle("Hello World")
                .setContent("This is a blog post"));
    }
}
