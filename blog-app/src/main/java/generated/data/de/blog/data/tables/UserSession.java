/*
 * This file is generated by jOOQ.
 */
package de.blog.data.tables;


import de.blog.data.Indexes;
import de.blog.data.Keys;
import de.blog.data.Public;
import de.blog.data.tables.records.UserSessionRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserSession extends TableImpl<UserSessionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.user_session</code>
     */
    public static final UserSession USER_SESSION = new UserSession();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserSessionRecord> getRecordType() {
        return UserSessionRecord.class;
    }

    /**
     * The column <code>public.user_session.primary_id</code>.
     */
    public final TableField<UserSessionRecord, String> PRIMARY_ID = createField(DSL.name("primary_id"), SQLDataType.CHAR(36).nullable(false), this, "");

    /**
     * The column <code>public.user_session.session_id</code>.
     */
    public final TableField<UserSessionRecord, String> SESSION_ID = createField(DSL.name("session_id"), SQLDataType.CHAR(36).nullable(false), this, "");

    /**
     * The column <code>public.user_session.creation_time</code>.
     */
    public final TableField<UserSessionRecord, Long> CREATION_TIME = createField(DSL.name("creation_time"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.user_session.last_access_time</code>.
     */
    public final TableField<UserSessionRecord, Long> LAST_ACCESS_TIME = createField(DSL.name("last_access_time"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.user_session.max_inactive_interval</code>.
     */
    public final TableField<UserSessionRecord, Integer> MAX_INACTIVE_INTERVAL = createField(DSL.name("max_inactive_interval"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.user_session.expiry_time</code>.
     */
    public final TableField<UserSessionRecord, Long> EXPIRY_TIME = createField(DSL.name("expiry_time"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.user_session.principal_name</code>.
     */
    public final TableField<UserSessionRecord, String> PRINCIPAL_NAME = createField(DSL.name("principal_name"), SQLDataType.VARCHAR(100), this, "");

    private UserSession(Name alias, Table<UserSessionRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserSession(Name alias, Table<UserSessionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.user_session</code> table reference
     */
    public UserSession(String alias) {
        this(DSL.name(alias), USER_SESSION);
    }

    /**
     * Create an aliased <code>public.user_session</code> table reference
     */
    public UserSession(Name alias) {
        this(alias, USER_SESSION);
    }

    /**
     * Create a <code>public.user_session</code> table reference
     */
    public UserSession() {
        this(DSL.name("user_session"), null);
    }

    public <O extends Record> UserSession(Table<O> child, ForeignKey<O, UserSessionRecord> key) {
        super(child, key, USER_SESSION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.SPRING_SESSION_IX_EXPIRY_TIME, Indexes.SPRING_SESSION_IX_PRINCIPAL_NAME, Indexes.SPRING_SESSION_IX_SESSION_ID);
    }

    @Override
    public UniqueKey<UserSessionRecord> getPrimaryKey() {
        return Keys.SPRING_SESSION_PK;
    }

    @Override
    public UserSession as(String alias) {
        return new UserSession(DSL.name(alias), this);
    }

    @Override
    public UserSession as(Name alias) {
        return new UserSession(alias, this);
    }

    @Override
    public UserSession as(Table<?> alias) {
        return new UserSession(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserSession rename(String name) {
        return new UserSession(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserSession rename(Name name) {
        return new UserSession(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserSession rename(Table<?> name) {
        return new UserSession(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, Long, Long, Integer, Long, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super String, ? super String, ? super Long, ? super Long, ? super Integer, ? super Long, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super String, ? super String, ? super Long, ? super Long, ? super Integer, ? super Long, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
