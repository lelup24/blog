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
  private final JwtTokenUtil jwtTokenUtil;
  private final UserEntityDao userEntityDao;
  private final SessionService sessionService;

  public UserLoginFilter(
      final DaoAuthenticationProvider authenticationProvider,
      final JwtTokenUtil jwtTokenUtil,
      final UserEntityDao userEntityDao,
      final SessionService sessionService) {
    this.authenticationProvider = authenticationProvider;
    this.jwtTokenUtil = jwtTokenUtil;
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

    final UserEntity userEntity =
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
    final String accessToken = jwtTokenUtil.createAccessToken(authResult);
    final String refreshToken = jwtTokenUtil.createRefreshToken(authResult);
    sessionService.setSession(refreshToken, request.getRemoteAddr());
    response.setHeader("access-token", accessToken);
    response.setHeader("refresh-token", refreshToken);
  }
}
