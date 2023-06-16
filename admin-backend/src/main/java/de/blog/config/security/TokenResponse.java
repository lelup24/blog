package de.blog.config.security;

import java.util.Objects;

public class TokenResponse {
  private String accessToken;
  private String refreshToken;

  public String getAccessToken() {
    return accessToken;
  }

  public TokenResponse setAccessToken(final String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public TokenResponse setRefreshToken(final String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final TokenResponse that = (TokenResponse) o;
    return Objects.equals(accessToken, that.accessToken)
        && Objects.equals(refreshToken, that.refreshToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, refreshToken);
  }
}
