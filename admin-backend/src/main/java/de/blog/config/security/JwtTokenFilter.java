package de.blog.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  private final SecurityUserService userService;
  private final JwtTokenUtils jwtTokenUtils;
  private final SessionService sessionService;

  public JwtTokenFilter(
      final SecurityUserService userService,
      final JwtTokenUtils jwtTokenUtils,
      final SessionService sessionService) {
    this.userService = userService;
    this.jwtTokenUtils = jwtTokenUtils;
    this.sessionService = sessionService;
  }

  @Override
  protected void doFilterInternal(
      final HttpServletRequest request,
      @NonNull final HttpServletResponse response,
      @NonNull final FilterChain filterChain)
      throws ServletException, IOException {

    final String header = request.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String token = header.split(" ")[1].trim();

    if (!jwtTokenUtils.validate(token)) {
      filterChain.doFilter(request, response);
      return;
    }

    final UserDetails userDetails =
        userService.loadUserByUsername(jwtTokenUtils.getUsername(token));

    final UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(), null, userDetails.getAuthorities());

    String refreshedToken = jwtTokenUtils.createToken(authenticationToken);

    // selber Seed?
    while (refreshedToken.equals(token)) {
      refreshedToken = jwtTokenUtils.createToken(authenticationToken);
    }

    if (!sessionService.validate(token, request.getRemoteAddr(), refreshedToken)) {
      filterChain.doFilter(request, response);
      return;
    }

    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    response.setHeader("auth-token", refreshedToken);

    filterChain.doFilter(request, response);
  }
}
