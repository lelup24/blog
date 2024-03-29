/*
 * This file is generated by jOOQ.
 */
package de.blog.data.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID userId;
    private String remoteAddress;
    private Boolean revoked;
    private String token;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session() {}

    public Session(Session value) {
        this.id = value.id;
        this.userId = value.userId;
        this.remoteAddress = value.remoteAddress;
        this.revoked = value.revoked;
        this.token = value.token;
        this.expiresAt = value.expiresAt;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public Session(
        UUID id,
        UUID userId,
        String remoteAddress,
        Boolean revoked,
        String token,
        LocalDateTime expiresAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.remoteAddress = remoteAddress;
        this.revoked = revoked;
        this.token = token;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>public.session.id</code>.
     */
    @NotNull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.session.id</code>.
     */
    public Session setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.session.user_id</code>.
     */
    public UUID getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.session.user_id</code>.
     */
    public Session setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Getter for <code>public.session.remote_address</code>.
     */
    @Size(max = 255)
    public String getRemoteAddress() {
        return this.remoteAddress;
    }

    /**
     * Setter for <code>public.session.remote_address</code>.
     */
    public Session setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
        return this;
    }

    /**
     * Getter for <code>public.session.revoked</code>.
     */
    public Boolean getRevoked() {
        return this.revoked;
    }

    /**
     * Setter for <code>public.session.revoked</code>.
     */
    public Session setRevoked(Boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    /**
     * Getter for <code>public.session.token</code>.
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Setter for <code>public.session.token</code>.
     */
    public Session setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Getter for <code>public.session.expires_at</code>.
     */
    public LocalDateTime getExpiresAt() {
        return this.expiresAt;
    }

    /**
     * Setter for <code>public.session.expires_at</code>.
     */
    public Session setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    /**
     * Getter for <code>public.session.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>public.session.created_at</code>.
     */
    public Session setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Getter for <code>public.session.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>public.session.updated_at</code>.
     */
    public Session setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Session other = (Session) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.remoteAddress == null) {
            if (other.remoteAddress != null)
                return false;
        }
        else if (!this.remoteAddress.equals(other.remoteAddress))
            return false;
        if (this.revoked == null) {
            if (other.revoked != null)
                return false;
        }
        else if (!this.revoked.equals(other.revoked))
            return false;
        if (this.token == null) {
            if (other.token != null)
                return false;
        }
        else if (!this.token.equals(other.token))
            return false;
        if (this.expiresAt == null) {
            if (other.expiresAt != null)
                return false;
        }
        else if (!this.expiresAt.equals(other.expiresAt))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!this.updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.remoteAddress == null) ? 0 : this.remoteAddress.hashCode());
        result = prime * result + ((this.revoked == null) ? 0 : this.revoked.hashCode());
        result = prime * result + ((this.token == null) ? 0 : this.token.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Session (");

        sb.append(id);
        sb.append(", ").append(userId);
        sb.append(", ").append(remoteAddress);
        sb.append(", ").append(revoked);
        sb.append(", ").append(token);
        sb.append(", ").append(expiresAt);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
