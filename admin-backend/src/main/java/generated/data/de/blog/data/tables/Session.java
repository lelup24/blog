/*
 * This file is generated by jOOQ.
 */
package de.blog.data.tables;


import de.blog.data.Keys;
import de.blog.data.Public;
import de.blog.data.tables.records.SessionRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function8;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row8;
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
public class Session extends TableImpl<SessionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.session</code>
     */
    public static final Session SESSION = new Session();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SessionRecord> getRecordType() {
        return SessionRecord.class;
    }

    /**
     * The column <code>public.session.id</code>.
     */
    public final TableField<SessionRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.session.user_id</code>.
     */
    public final TableField<SessionRecord, UUID> USER_ID = createField(DSL.name("user_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.session.remote_address</code>.
     */
    public final TableField<SessionRecord, String> REMOTE_ADDRESS = createField(DSL.name("remote_address"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.session.revoked</code>.
     */
    public final TableField<SessionRecord, Boolean> REVOKED = createField(DSL.name("revoked"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.session.token</code>.
     */
    public final TableField<SessionRecord, String> TOKEN = createField(DSL.name("token"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.session.expires_at</code>.
     */
    public final TableField<SessionRecord, LocalDateTime> EXPIRES_AT = createField(DSL.name("expires_at"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.session.created_at</code>.
     */
    public final TableField<SessionRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.session.updated_at</code>.
     */
    public final TableField<SessionRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("updated_at"), SQLDataType.LOCALDATETIME(6), this, "");

    private Session(Name alias, Table<SessionRecord> aliased) {
        this(alias, aliased, null);
    }

    private Session(Name alias, Table<SessionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.session</code> table reference
     */
    public Session(String alias) {
        this(DSL.name(alias), SESSION);
    }

    /**
     * Create an aliased <code>public.session</code> table reference
     */
    public Session(Name alias) {
        this(alias, SESSION);
    }

    /**
     * Create a <code>public.session</code> table reference
     */
    public Session() {
        this(DSL.name("session"), null);
    }

    public <O extends Record> Session(Table<O> child, ForeignKey<O, SessionRecord> key) {
        super(child, key, SESSION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<SessionRecord> getPrimaryKey() {
        return Keys.SESSION_PKEY;
    }

    @Override
    public List<UniqueKey<SessionRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.SESSION_TOKEN_KEY);
    }

    @Override
    public List<ForeignKey<SessionRecord, ?>> getReferences() {
        return Arrays.asList(Keys.SESSION__SESSION_USER_ID_FKEY);
    }

    private transient UserEntity _userEntity;

    /**
     * Get the implicit join path to the <code>public.user_entity</code> table.
     */
    public UserEntity userEntity() {
        if (_userEntity == null)
            _userEntity = new UserEntity(this, Keys.SESSION__SESSION_USER_ID_FKEY);

        return _userEntity;
    }

    @Override
    public Session as(String alias) {
        return new Session(DSL.name(alias), this);
    }

    @Override
    public Session as(Name alias) {
        return new Session(alias, this);
    }

    @Override
    public Session as(Table<?> alias) {
        return new Session(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Session rename(String name) {
        return new Session(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Session rename(Name name) {
        return new Session(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Session rename(Table<?> name) {
        return new Session(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<UUID, UUID, String, Boolean, String, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super UUID, ? super UUID, ? super String, ? super Boolean, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super UUID, ? super UUID, ? super String, ? super Boolean, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
