/*
 * This file is generated by jOOQ.
 */
package de.blog.data.tables.pojos;


import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID sessionId;
    private String token;
    private LocalDateTime createdAt;

    public Token() {}

    public Token(Token value) {
        this.id = value.id;
        this.sessionId = value.sessionId;
        this.token = value.token;
        this.createdAt = value.createdAt;
    }

    public Token(
        UUID id,
        UUID sessionId,
        String token,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.sessionId = sessionId;
        this.token = token;
        this.createdAt = createdAt;
    }

    /**
     * Getter for <code>public.token.id</code>.
     */
    @NotNull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.token.id</code>.
     */
    public Token setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.token.session_id</code>.
     */
    public UUID getSessionId() {
        return this.sessionId;
    }

    /**
     * Setter for <code>public.token.session_id</code>.
     */
    public Token setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * Getter for <code>public.token.token</code>.
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Setter for <code>public.token.token</code>.
     */
    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Getter for <code>public.token.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>public.token.created_at</code>.
     */
    public Token setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        final Token other = (Token) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.sessionId == null) {
            if (other.sessionId != null)
                return false;
        }
        else if (!this.sessionId.equals(other.sessionId))
            return false;
        if (this.token == null) {
            if (other.token != null)
                return false;
        }
        else if (!this.token.equals(other.token))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.sessionId == null) ? 0 : this.sessionId.hashCode());
        result = prime * result + ((this.token == null) ? 0 : this.token.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Token (");

        sb.append(id);
        sb.append(", ").append(sessionId);
        sb.append(", ").append(token);
        sb.append(", ").append(createdAt);

        sb.append(")");
        return sb.toString();
    }
}
