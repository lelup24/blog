package de.blog.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {



  @GetMapping
  public ResponseEntity<String> getUsers() {
    return ResponseEntity.ok("{\"msg\":\"Hello World\"}");
  }

  @GetMapping("/2")
  public ResponseEntity<String> getUsers2() {
    return ResponseEntity.ok("{\"msg\":\"Hallo Welt\"}");
  }


}
