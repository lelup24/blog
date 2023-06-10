package de.blog.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.blog.data.tables.daos.UserEntityDao;
import de.blog.data.tables.pojos.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

public class UserLoginFilter extends UsernamePasswordAuthenticationFilter {

  private final DaoAuthenticationProvider authenticationProvider;
  private final JwtTokenUtils jwtTokenUtils;
  private final UserEntityDao userEntityDao;
  private final SessionService sessionService;

  public UserLoginFilter(
          final DaoAuthenticationProvider authenticationProvider,
          final JwtTokenUtils jwtTokenUtils,
          final UserEntityDao userEntityDao, final SessionService sessionService) {
    this.authenticationProvider = authenticationProvider;
    this.jwtTokenUtils = jwtTokenUtils;
    this.userEntityDao = userEntityDao;
    this.sessionService = sessionService;
  }

  @Transactional
  @Override
  public Authentication attemptAuthentication(
      final HttpServletRequest request, final HttpServletResponse response)
      throws AuthenticationException {

    final UserLoginModel userLoginModel;

    try {
      userLoginModel = new ObjectMapper().readValue(request.getInputStream(), UserLoginModel.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    UserEntity userEntity =
        userEntityDao
            .fetchOptionalByUsername(userLoginModel.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    final UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            userLoginModel.getUsername(), userEntity.getSalt() + userLoginModel.getPassword());

    return authenticationProvider.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain chain,
      final Authentication authResult) {

    final String token = jwtTokenUtils.createToken(authResult);
    sessionService.setSession(token, request.getRemoteAddr());
    response.setHeader("auth-token", token);
  }
}
