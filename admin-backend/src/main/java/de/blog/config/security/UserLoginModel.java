package de.blog.config.security;

import java.util.Objects;

public class UserLoginModel {

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public UserLoginModel setUsername(final String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserLoginModel setPassword(final String password) {
    this.password = password;
    return this;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final UserLoginModel that = (UserLoginModel) o;
    return Objects.equals(username, that.username) && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }
}
