package de.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class RsaProperties {

    private String privateKey;
    private String publicKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public RsaProperties setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public RsaProperties setPublicKey(final String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RsaProperties that = (RsaProperties) o;
        return Objects.equals(privateKey, that.privateKey) && Objects.equals(publicKey, that.publicKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privateKey, publicKey);
    }
}
