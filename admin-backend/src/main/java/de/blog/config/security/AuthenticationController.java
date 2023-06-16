package de.blog.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

  private final SessionService sessionService;
  private final AuthenticationService authenticationService;

  public AuthenticationController(
      final SessionService sessionService, final AuthenticationService authenticationService) {
    this.sessionService = sessionService;
    this.authenticationService = authenticationService;
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout() {
    this.sessionService.removeSessions();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<TokenResponse> refreshToken(HttpServletRequest request) {
    return new ResponseEntity<>(authenticationService.refreshToken(request), HttpStatus.OK);
  }
}
