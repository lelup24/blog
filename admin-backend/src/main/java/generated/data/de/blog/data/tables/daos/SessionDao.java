/*
 * This file is generated by jOOQ.
 */
package de.blog.data.tables.daos;


import de.blog.data.tables.Session;
import de.blog.data.tables.records.SessionRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Repository
public class SessionDao extends DAOImpl<SessionRecord, de.blog.data.tables.pojos.Session, UUID> {

    /**
     * Create a new SessionDao without any configuration
     */
    public SessionDao() {
        super(Session.SESSION, de.blog.data.tables.pojos.Session.class);
    }

    /**
     * Create a new SessionDao with an attached configuration
     */
    @Autowired
    public SessionDao(Configuration configuration) {
        super(Session.SESSION, de.blog.data.tables.pojos.Session.class, configuration);
    }

    @Override
    public UUID getId(de.blog.data.tables.pojos.Session object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Session.SESSION.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchById(UUID... values) {
        return fetch(Session.SESSION.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public de.blog.data.tables.pojos.Session fetchOneById(UUID value) {
        return fetchOne(Session.SESSION.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<de.blog.data.tables.pojos.Session> fetchOptionalById(UUID value) {
        return fetchOptional(Session.SESSION.ID, value);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchRangeOfUserId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Session.SESSION.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchByUserId(UUID... values) {
        return fetch(Session.SESSION.USER_ID, values);
    }

    /**
     * Fetch records that have <code>remote_address BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchRangeOfRemoteAddress(String lowerInclusive, String upperInclusive) {
        return fetchRange(Session.SESSION.REMOTE_ADDRESS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>remote_address IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchByRemoteAddress(String... values) {
        return fetch(Session.SESSION.REMOTE_ADDRESS, values);
    }

    /**
     * Fetch records that have <code>revoked BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchRangeOfRevoked(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Session.SESSION.REVOKED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>revoked IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchByRevoked(Boolean... values) {
        return fetch(Session.SESSION.REVOKED, values);
    }

    /**
     * Fetch records that have <code>token BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchRangeOfToken(String lowerInclusive, String upperInclusive) {
        return fetchRange(Session.SESSION.TOKEN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>token IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchByToken(String... values) {
        return fetch(Session.SESSION.TOKEN, values);
    }

    /**
     * Fetch a unique record that has <code>token = value</code>
     */
    public de.blog.data.tables.pojos.Session fetchOneByToken(String value) {
        return fetchOne(Session.SESSION.TOKEN, value);
    }

    /**
     * Fetch a unique record that has <code>token = value</code>
     */
    public Optional<de.blog.data.tables.pojos.Session> fetchOptionalByToken(String value) {
        return fetchOptional(Session.SESSION.TOKEN, value);
    }

    /**
     * Fetch records that have <code>expires_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchRangeOfExpiresAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Session.SESSION.EXPIRES_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>expires_at IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchByExpiresAt(LocalDateTime... values) {
        return fetch(Session.SESSION.EXPIRES_AT, values);
    }

    /**
     * Fetch records that have <code>created_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchRangeOfCreatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Session.SESSION.CREATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(Session.SESSION.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchRangeOfUpdatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Session.SESSION.UPDATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Session> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(Session.SESSION.UPDATED_AT, values);
    }
}
