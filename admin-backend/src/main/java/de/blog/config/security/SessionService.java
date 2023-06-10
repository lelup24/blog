package de.blog.config.security;

import static de.blog.config.ClockConfig.DEFAULT_ZONE_ID;

import de.blog.common.CurrentUser;
import de.blog.data.tables.daos.SessionDao;
import de.blog.data.tables.daos.UserEntityDao;
import de.blog.data.tables.pojos.Session;
import de.blog.data.tables.pojos.UserEntity;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
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
  private final CurrentUser currentUser;

  public SessionService(
      final SessionDao sessionDao,
      final JwtTokenUtils jwtTokenUtils,
      final UserEntityDao userEntityDao,
      final Clock clock,
      final CurrentUser currentUser) {
    this.sessionDao = sessionDao;
    this.jwtTokenUtils = jwtTokenUtils;
    this.userEntityDao = userEntityDao;
    this.clock = clock;
    this.currentUser = currentUser;
  }

  @Transactional
  public void setSession(final String token, final String remoteAddr) {

    final UserEntity userEntity =
        userEntityDao
            .fetchOptionalByUsername(jwtTokenUtils.getUsername(token))
            .orElseThrow(() -> new RuntimeException("User not found"));

    final List<Session> oldSessions = sessionDao.fetchByUserId(userEntity.getId());

    sessionDao.delete(oldSessions);

    final LocalDateTime now = LocalDateTime.now(clock);
    final LocalDateTime expiresAt =
        jwtTokenUtils.getExpiresAt(token).toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
    final Session session =
        new Session()
            .setId(UUID.randomUUID())
            .setRevoked(Boolean.FALSE)
            .setRemoteAddress(remoteAddr)
            .setUserId(userEntity.getId())
            .setExpiresAt(expiresAt)
            .setToken(token)
            .setCreatedAt(now)
            .setUpdatedAt(now);

    sessionDao.insert(session);
  }

  @Transactional
  public void updateSession(String oldToken, String refreshedToken) {

    Optional<Session> sessionOptional = sessionDao.fetchOptionalByToken(oldToken);

    if (sessionOptional.isEmpty()) {
      return;
    }

    Session session = sessionOptional.get();

    sessionDao.update(session.setUpdatedAt(LocalDateTime.now(clock)));

    final LocalDateTime expiresAt =
        jwtTokenUtils
            .getExpiresAt(refreshedToken)
            .toInstant()
            .atZone(DEFAULT_ZONE_ID)
            .toLocalDateTime();

    sessionDao.update(
        session
            .setToken(refreshedToken)
            .setExpiresAt(expiresAt)
            .setUpdatedAt(LocalDateTime.now(clock)));
  }

  @Transactional
  public boolean validate(String token, String remoteAddr) {
    Optional<Session> sessionOptional = sessionDao.fetchOptionalByToken(token);

    if (sessionOptional.isEmpty()) {
      Optional<UserEntity> userEntity =
          userEntityDao.fetchOptionalByUsername(jwtTokenUtils.getUsername(token));

      if (userEntity.isEmpty()) {
        return false;
      }

      List<Session> sessions = sessionDao.fetchByUserId(userEntity.get().getId());

      sessions.forEach(session -> session.setRevoked(Boolean.TRUE));
      sessionDao.update(sessions);

      return false;
    }

    Session session = sessionOptional.get();

    if (LocalDateTime.now(clock).isAfter(session.getExpiresAt())) {
      return false;
    }

    if (session.getRevoked()) {
      return false;
    }

    if (!session.getRemoteAddress().equals(remoteAddr)) {
      sessionDao.update(session.setRevoked(Boolean.TRUE).setUpdatedAt(LocalDateTime.now(clock)));
      return false;
    }

    return true;
  }

  @Transactional
  public void removeSessions() {
    final List<Session> sessions = sessionDao.fetchByUserId(currentUser.getId());
    sessionDao.delete(sessions);
  }
}
