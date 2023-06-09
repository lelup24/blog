package de.blog.config;

import static de.blog.data.tables.Session.SESSION;

import de.blog.data.tables.daos.SessionDao;
import de.blog.data.tables.daos.TokenDao;
import de.blog.data.tables.daos.UserEntityDao;
import de.blog.data.tables.pojos.Session;
import de.blog.data.tables.pojos.Token;
import de.blog.data.tables.pojos.UserEntity;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {
  private final JwtTokenUtils jwtTokenUtils;
  private final Clock clock;
  private final SessionDao sessionDao;
  private final UserEntityDao userEntityDao;
  private final TokenDao tokenDao;

  public SessionService(
      final SessionDao sessionDao,
      final JwtTokenUtils jwtTokenUtils,
      final UserEntityDao userEntityDao,
      final Clock clock,
      final TokenDao tokenDao) {
    this.sessionDao = sessionDao;
    this.jwtTokenUtils = jwtTokenUtils;
    this.userEntityDao = userEntityDao;
    this.clock = clock;
    this.tokenDao = tokenDao;
  }

  @Transactional
  public void setSession(final String token, final String remoteAddr) {

    UserEntity userEntity =
        userEntityDao
            .fetchOptionalByUsername(jwtTokenUtils.getUsername(token))
            .orElseThrow(() -> new RuntimeException("User not found"));

    LocalDateTime now = LocalDateTime.now(clock);
    Session session =
        new Session()
            .setId(UUID.randomUUID())
            .setRevoked(Boolean.FALSE)
            .setRemoteAddress(remoteAddr)
            .setUserId(userEntity.getId())
            .setCreatedAt(now)
            .setUpdatedAt(now);

    sessionDao.insert(session);

    tokenDao.insert(
        new Token()
            .setId(UUID.randomUUID())
            .setSessionId(session.getId())
            .setToken(token)
            .setCreatedAt(now));
  }

  @Transactional
  public void updateSession(String oldToken, String refreshedToken) {
    Optional<Token> tokenOptional = tokenDao.fetchOptionalByToken(oldToken);

    if (tokenOptional.isEmpty()) {
      return;
    }

    Token tokenEntity = tokenOptional.get();

    Optional<Session> sessionOptional = sessionDao.findOptionalById(tokenEntity.getSessionId());

    if (sessionOptional.isEmpty()) {
      return;
    }

    Session session = sessionOptional.get();

    sessionDao.update(session.setUpdatedAt(LocalDateTime.now(clock)));

    tokenDao.update(tokenEntity.setToken(refreshedToken).setCreatedAt(LocalDateTime.now(clock)));
  }

  boolean validate(String token, String remoteAddr) {
    Optional<Token> tokenOptional = tokenDao.fetchOptionalByToken(token);

    if (tokenOptional.isEmpty()) {
      String username = jwtTokenUtils.getUsername(token);
      Optional<UserEntity> userOptional = userEntityDao.fetchOptionalByUsername(username);

      if (userOptional.isEmpty()) {
        return false;
      }

      Optional<Session> session =
          sessionDao.fetchOptional(SESSION.USER_ID, userOptional.get().getId());

      if (session.isEmpty()) {
        return false;
      }

      sessionDao.update(
          session.get().setRevoked(Boolean.TRUE).setUpdatedAt(LocalDateTime.now(clock)));

      return false;
    }

    Token tokenEntity = tokenOptional.get();

    Optional<Session> sessionOptional = sessionDao.findOptionalById(tokenEntity.getSessionId());

    if (sessionOptional.isEmpty()) {
      return false;
    }

    Session session = sessionOptional.get();

    if (session.getRevoked()) {
      return false;
    }

    if (!session.getRemoteAddress().equals(remoteAddr)) {
      sessionDao.update(session.setRevoked(Boolean.TRUE).setUpdatedAt(LocalDateTime.now(clock)));
      return false;
    }

    return true;
  }

  public void deleteToken(final String token) {
    tokenDao.delete(tokenDao.fetchByToken(token));
  }
}
