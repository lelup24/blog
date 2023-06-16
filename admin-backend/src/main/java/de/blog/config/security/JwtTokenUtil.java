package de.blog.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

  private RSAPrivateKey privateKey;
  private RSAPublicKey publicKey;

  private final Algorithm algorithm;
  private final RsaProperties rsaProperties;

  public JwtTokenUtil(final RsaProperties rsaProperties) {
    this.rsaProperties = rsaProperties;
    loadKeys();
    algorithm = Algorithm.RSA256(publicKey, privateKey);
  }

  public String createRefreshToken(final Authentication authentication) {

    return JWT.create()
        .withSubject(authentication.getName())
        .withIssuer("blog-backend")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(new Date().getTime() + 1800000))
        .sign(algorithm);
  }

  public String createAccessToken(final Authentication authentication) {

    List<String> roles =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .map(role -> role.replace("ROLE_", ""))
            .toList();

    return JWT.create()
        .withSubject(authentication.getName())
        .withIssuer("blog-backend")
        .withClaim("roles", roles)
        .withIssuedAt(new Date())
        //            .withExpiresAt(new Date(new Date().getTime() + 300000))
        .withExpiresAt(new Date(new Date().getTime() + 30000))
        .sign(algorithm);
  }

  public Boolean validate(final String tokenAsString) {
    try {
      JWT.require(algorithm).build().verify(tokenAsString);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String getUsername(final String token) {
    return JWT.require(algorithm).build().verify(token).getSubject();
  }

  public Date getExpiresAt(String token) {
    return JWT.require(algorithm).build().verify(token).getExpiresAt();
  }

  private void loadKeys() {
    try {
      final KeyFactory kf = KeyFactory.getInstance("RSA");
      privateKey =
          (RSAPrivateKey)
              kf.generatePrivate(
                  new PKCS8EncodedKeySpec(
                      Base64.getDecoder().decode(rsaProperties.getPrivateKey())));
      publicKey =
          (RSAPublicKey)
              kf.generatePublic(
                  new X509EncodedKeySpec(Base64.getDecoder().decode(rsaProperties.getPublicKey())));

    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }
}
