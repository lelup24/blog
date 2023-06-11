/*
 * This file is generated by jOOQ.
 */
package de.blog.data.tables.daos;


import de.blog.data.tables.Role;
import de.blog.data.tables.records.RoleRecord;

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
public class RoleDao extends DAOImpl<RoleRecord, de.blog.data.tables.pojos.Role, UUID> {

    /**
     * Create a new RoleDao without any configuration
     */
    public RoleDao() {
        super(Role.ROLE, de.blog.data.tables.pojos.Role.class);
    }

    /**
     * Create a new RoleDao with an attached configuration
     */
    @Autowired
    public RoleDao(Configuration configuration) {
        super(Role.ROLE, de.blog.data.tables.pojos.Role.class, configuration);
    }

    @Override
    public UUID getId(de.blog.data.tables.pojos.Role object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Role.ROLE.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchById(UUID... values) {
        return fetch(Role.ROLE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public de.blog.data.tables.pojos.Role fetchOneById(UUID value) {
        return fetchOne(Role.ROLE.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<de.blog.data.tables.pojos.Role> fetchOptionalById(UUID value) {
        return fetchOptional(Role.ROLE.ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Role.ROLE.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchByName(String... values) {
        return fetch(Role.ROLE.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public de.blog.data.tables.pojos.Role fetchOneByName(String value) {
        return fetchOne(Role.ROLE.NAME, value);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public Optional<de.blog.data.tables.pojos.Role> fetchOptionalByName(String value) {
        return fetchOptional(Role.ROLE.NAME, value);
    }

    /**
     * Fetch records that have <code>created_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchRangeOfCreatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Role.ROLE.CREATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(Role.ROLE.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchRangeOfCreatedBy(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Role.ROLE.CREATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchByCreatedBy(UUID... values) {
        return fetch(Role.ROLE.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>updated_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchRangeOfUpdatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Role.ROLE.UPDATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(Role.ROLE.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchRangeOfUpdatedBy(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Role.ROLE.UPDATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_by IN (values)</code>
     */
    public List<de.blog.data.tables.pojos.Role> fetchByUpdatedBy(UUID... values) {
        return fetch(Role.ROLE.UPDATED_BY, values);
    }
}