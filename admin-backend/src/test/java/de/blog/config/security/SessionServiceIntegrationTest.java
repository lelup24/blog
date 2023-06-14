package de.blog.config.security;

import static de.blog.config.ClockConfig.DEFAULT_ZONE_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import config.IntegrationTest;
import de.blog.data.tables.daos.SessionDao;
import de.blog.data.tables.pojos.Session;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@IntegrationTest
class SessionServiceIntegrationTest {

  private static final String USERNAME = "peter";
  private static final String PASSWORD = "1234";

  private final TestRestTemplate testRestTemplate;
  private final SessionDao sessionDao;
  @MockBean Clock clock;

  @Autowired
  SessionServiceIntegrationTest(
      final TestRestTemplate testRestTemplate, final SessionDao sessionDao) {
    this.testRestTemplate = testRestTemplate;
    this.sessionDao = sessionDao;
  }

  @AfterEach
  void afterEach() {
    sessionDao.delete(sessionDao.findAll());
  }

  @Test
  void set_session() {

    // init
    UserLoginModel userLoginModel =
        new UserLoginModel().setUsername(USERNAME).setPassword(PASSWORD);
    HttpEntity<UserLoginModel> request = new HttpEntity<>(userLoginModel);

    // run
    ResponseEntity<Void> response =
        testRestTemplate.exchange("/api/login", HttpMethod.POST, request, Void.class);

    // assert
    String token = response.getHeaders().getFirst("auth-token");

    Optional<Session> session = sessionDao.fetchOptionalByToken(token);

    assertThat(session).isNotEmpty();
  }

  @Test
  void same_token_403() {
    // init
    UserLoginModel userLoginModel =
        new UserLoginModel().setUsername(USERNAME).setPassword(PASSWORD);
    HttpEntity<UserLoginModel> request = new HttpEntity<>(userLoginModel);

    ResponseEntity<Void> response =
        testRestTemplate.exchange("/api/login", HttpMethod.POST, request, Void.class);

    String token = response.getHeaders().getFirst("auth-token");

    // run
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);

    HttpEntity<Object> refreshRequest = new HttpEntity<>(null, httpHeaders);

    ResponseEntity<Void> refreshResponse_1 =
        testRestTemplate.exchange(
            "/api/v1/users/refresh", HttpMethod.POST, refreshRequest, Void.class);


    ResponseEntity<String> refreshResponse_2 =
        testRestTemplate.exchange(
            "/api/v1/users/refresh", HttpMethod.GET, refreshRequest, String.class);

    // assert
    assertThat(refreshResponse_1.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(refreshResponse_2.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
  }

  @Test
  void is_session_expired() {
    // init
    UserLoginModel userLoginModel =
        new UserLoginModel().setUsername(USERNAME).setPassword(PASSWORD);
    HttpEntity<UserLoginModel> request = new HttpEntity<>(userLoginModel);

    ResponseEntity<Void> response =
        testRestTemplate.exchange("/api/login", HttpMethod.POST, request, Void.class);

    String token = response.getHeaders().getFirst("auth-token");

    // run
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);

    HttpEntity<Object> refreshRequest = new HttpEntity<>(null, httpHeaders);

    ResponseEntity<Void> refreshResponse =
        testRestTemplate.exchange(
            "/api/v1/users/refresh", HttpMethod.POST, refreshRequest, Void.class);

    String refreshedToken = refreshResponse.getHeaders().getFirst("auth-token");

    // assert
    assertThat(refreshResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    // run
    when(clock.instant())
        .thenReturn(LocalDateTime.now().plusHours(2L).atZone(DEFAULT_ZONE_ID).toInstant());

    HttpHeaders httpHeadersExpired = new HttpHeaders();
    httpHeadersExpired.set(HttpHeaders.AUTHORIZATION, "Bearer + " + refreshedToken);

    HttpEntity<Object> expiredRequest = new HttpEntity<>(null, httpHeadersExpired);

    ResponseEntity<Void> expiredResponse =
        testRestTemplate.exchange(
            "/api/v1/users/refresh", HttpMethod.POST, expiredRequest, Void.class);

    // assert
    assertThat(expiredResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
  }
}
