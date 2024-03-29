/*
 * This file is generated by jOOQ.
 */
package de.blog.data.tables.records;


import de.blog.data.tables.UserEntity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserEntityRecord extends UpdatableRecordImpl<UserEntityRecord> implements Record12<UUID, String, String, String, String, Boolean, String, String, LocalDateTime, UUID, LocalDateTime, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.user_entity.id</code>.
     */
    public UserEntityRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.id</code>.
     */
    @NotNull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.user_entity.username</code>.
     */
    public UserEntityRecord setUsername(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.username</code>.
     */
    @Size(max = 255)
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.user_entity.password</code>.
     */
    public UserEntityRecord setPassword(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.password</code>.
     */
    @Size(max = 255)
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.user_entity.salt</code>.
     */
    public UserEntityRecord setSalt(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.salt</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getSalt() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.user_entity.email</code>.
     */
    public UserEntityRecord setEmail(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.email</code>.
     */
    @Size(max = 255)
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.user_entity.email_verified</code>.
     */
    public UserEntityRecord setEmailVerified(Boolean value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.email_verified</code>.
     */
    public Boolean getEmailVerified() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>public.user_entity.firstname</code>.
     */
    public UserEntityRecord setFirstname(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.firstname</code>.
     */
    @Size(max = 255)
    public String getFirstname() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.user_entity.lastname</code>.
     */
    public UserEntityRecord setLastname(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.lastname</code>.
     */
    @Size(max = 255)
    public String getLastname() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.user_entity.created_at</code>.
     */
    public UserEntityRecord setCreatedAt(LocalDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(8);
    }

    /**
     * Setter for <code>public.user_entity.created_by</code>.
     */
    public UserEntityRecord setCreatedBy(UUID value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.created_by</code>.
     */
    public UUID getCreatedBy() {
        return (UUID) get(9);
    }

    /**
     * Setter for <code>public.user_entity.updated_at</code>.
     */
    public UserEntityRecord setUpdatedAt(LocalDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>public.user_entity.updated_by</code>.
     */
    public UserEntityRecord setUpdatedBy(UUID value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public.user_entity.updated_by</code>.
     */
    public UUID getUpdatedBy() {
        return (UUID) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<UUID, String, String, String, String, Boolean, String, String, LocalDateTime, UUID, LocalDateTime, UUID> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<UUID, String, String, String, String, Boolean, String, String, LocalDateTime, UUID, LocalDateTime, UUID> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return UserEntity.USER_ENTITY.ID;
    }

    @Override
    public Field<String> field2() {
        return UserEntity.USER_ENTITY.USERNAME;
    }

    @Override
    public Field<String> field3() {
        return UserEntity.USER_ENTITY.PASSWORD;
    }

    @Override
    public Field<String> field4() {
        return UserEntity.USER_ENTITY.SALT;
    }

    @Override
    public Field<String> field5() {
        return UserEntity.USER_ENTITY.EMAIL;
    }

    @Override
    public Field<Boolean> field6() {
        return UserEntity.USER_ENTITY.EMAIL_VERIFIED;
    }

    @Override
    public Field<String> field7() {
        return UserEntity.USER_ENTITY.FIRSTNAME;
    }

    @Override
    public Field<String> field8() {
        return UserEntity.USER_ENTITY.LASTNAME;
    }

    @Override
    public Field<LocalDateTime> field9() {
        return UserEntity.USER_ENTITY.CREATED_AT;
    }

    @Override
    public Field<UUID> field10() {
        return UserEntity.USER_ENTITY.CREATED_BY;
    }

    @Override
    public Field<LocalDateTime> field11() {
        return UserEntity.USER_ENTITY.UPDATED_AT;
    }

    @Override
    public Field<UUID> field12() {
        return UserEntity.USER_ENTITY.UPDATED_BY;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public String component3() {
        return getPassword();
    }

    @Override
    public String component4() {
        return getSalt();
    }

    @Override
    public String component5() {
        return getEmail();
    }

    @Override
    public Boolean component6() {
        return getEmailVerified();
    }

    @Override
    public String component7() {
        return getFirstname();
    }

    @Override
    public String component8() {
        return getLastname();
    }

    @Override
    public LocalDateTime component9() {
        return getCreatedAt();
    }

    @Override
    public UUID component10() {
        return getCreatedBy();
    }

    @Override
    public LocalDateTime component11() {
        return getUpdatedAt();
    }

    @Override
    public UUID component12() {
        return getUpdatedBy();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public String value3() {
        return getPassword();
    }

    @Override
    public String value4() {
        return getSalt();
    }

    @Override
    public String value5() {
        return getEmail();
    }

    @Override
    public Boolean value6() {
        return getEmailVerified();
    }

    @Override
    public String value7() {
        return getFirstname();
    }

    @Override
    public String value8() {
        return getLastname();
    }

    @Override
    public LocalDateTime value9() {
        return getCreatedAt();
    }

    @Override
    public UUID value10() {
        return getCreatedBy();
    }

    @Override
    public LocalDateTime value11() {
        return getUpdatedAt();
    }

    @Override
    public UUID value12() {
        return getUpdatedBy();
    }

    @Override
    public UserEntityRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public UserEntityRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public UserEntityRecord value3(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UserEntityRecord value4(String value) {
        setSalt(value);
        return this;
    }

    @Override
    public UserEntityRecord value5(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UserEntityRecord value6(Boolean value) {
        setEmailVerified(value);
        return this;
    }

    @Override
    public UserEntityRecord value7(String value) {
        setFirstname(value);
        return this;
    }

    @Override
    public UserEntityRecord value8(String value) {
        setLastname(value);
        return this;
    }

    @Override
    public UserEntityRecord value9(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public UserEntityRecord value10(UUID value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public UserEntityRecord value11(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public UserEntityRecord value12(UUID value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    public UserEntityRecord values(UUID value1, String value2, String value3, String value4, String value5, Boolean value6, String value7, String value8, LocalDateTime value9, UUID value10, LocalDateTime value11, UUID value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserEntityRecord
     */
    public UserEntityRecord() {
        super(UserEntity.USER_ENTITY);
    }

    /**
     * Create a detached, initialised UserEntityRecord
     */
    public UserEntityRecord(UUID id, String username, String password, String salt, String email, Boolean emailVerified, String firstname, String lastname, LocalDateTime createdAt, UUID createdBy, LocalDateTime updatedAt, UUID updatedBy) {
        super(UserEntity.USER_ENTITY);

        setId(id);
        setUsername(username);
        setPassword(password);
        setSalt(salt);
        setEmail(email);
        setEmailVerified(emailVerified);
        setFirstname(firstname);
        setLastname(lastname);
        setCreatedAt(createdAt);
        setCreatedBy(createdBy);
        setUpdatedAt(updatedAt);
        setUpdatedBy(updatedBy);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised UserEntityRecord
     */
    public UserEntityRecord(de.blog.data.tables.pojos.UserEntity value) {
        super(UserEntity.USER_ENTITY);

        if (value != null) {
            setId(value.getId());
            setUsername(value.getUsername());
            setPassword(value.getPassword());
            setSalt(value.getSalt());
            setEmail(value.getEmail());
            setEmailVerified(value.getEmailVerified());
            setFirstname(value.getFirstname());
            setLastname(value.getLastname());
            setCreatedAt(value.getCreatedAt());
            setCreatedBy(value.getCreatedBy());
            setUpdatedAt(value.getUpdatedAt());
            setUpdatedBy(value.getUpdatedBy());
            resetChangedOnNotNull();
        }
    }
}
