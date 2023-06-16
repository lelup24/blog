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
  private final JwtTokenUtil jwtTokenUtil;

  public JwtTokenFilter(final SecurityUserService userService, final JwtTokenUtil jwtTokenUtil) {
    this.userService = userService;
    this.jwtTokenUtil = jwtTokenUtil;
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

    if (!jwtTokenUtil.validate(token)) {
      filterChain.doFilter(request, response);
      return;
    }

    final UserDetails userDetails = userService.loadUserByUsername(jwtTokenUtil.getUsername(token));

    final UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(), null, userDetails.getAuthorities());

    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    filterChain.doFilter(request, response);
  }
}
