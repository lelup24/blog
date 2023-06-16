package de.blog.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
    final String header = request.getHeader("Authorization");

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
      throw new RuntimeException();
    }

    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    String accessToken = jwtTokenUtil.createAccessToken(authenticationToken);

    sessionService.updateSession(token, refreshToken);

    return new TokenResponse().setAccessToken(accessToken).setRefreshToken(refreshToken);
  }
}
