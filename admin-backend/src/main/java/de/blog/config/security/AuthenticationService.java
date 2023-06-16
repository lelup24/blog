package de.blog.config.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final JwtTokenUtil jwtTokenUtil;
  private final SessionService sessionService;
  private final SecurityUserService userService;

  public AuthenticationService(
      final JwtTokenUtil jwtTokenUtil,
      final SessionService sessionService,
      final SecurityUserService userService) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.sessionService = sessionService;
    this.userService = userService;
  }

  public TokenResponse refreshToken(HttpServletRequest request) {
    final String header = request.getHeader(AUTHORIZATION);

    if (header == null || !header.startsWith("Bearer ")) {
      throw new RuntimeException();
    }

    final String token = header.split(" ")[1].trim();

    if (!jwtTokenUtil.validate(token)) {
      throw new RuntimeException();
    }

    final UserDetails userDetails = userService.loadUserByUsername(jwtTokenUtil.getUsername(token));

    final UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(), null, userDetails.getAuthorities());

    String refreshToken = jwtTokenUtil.createRefreshToken(authenticationToken);

    // selber Seed?
    while (refreshToken.equals(token)) {
      refreshToken = jwtTokenUtil.createRefreshToken(authenticationToken);
    }

    if (!sessionService.validate(token, request.getRemoteAddr())) {
      throw new RuntimeException("Invalid session");
    }
    
    String accessToken = jwtTokenUtil.createAccessToken(authenticationToken);

    sessionService.updateSession(token, refreshToken);

    return new TokenResponse().setAccessToken(accessToken).setRefreshToken(refreshToken);
  }
}
