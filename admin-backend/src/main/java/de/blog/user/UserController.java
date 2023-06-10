package de.blog.user;

import de.blog.config.security.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final SessionService sessionService;

  public UserController(final SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @GetMapping
  public ResponseEntity<String> getUsers() {
    return ResponseEntity.ok("{\"msg\":\"Hello World\"}");
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout() {
    this.sessionService.removeSessions();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/refresh")
  public ResponseEntity<Void> refresh() {
    return ResponseEntity.ok().build();
  }
}
