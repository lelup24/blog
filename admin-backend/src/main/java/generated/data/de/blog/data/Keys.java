/*
 * This file is generated by jOOQ.
 */
package de.blog.data;


import de.blog.data.tables.Post;
import de.blog.data.tables.Role;
import de.blog.data.tables.Session;
import de.blog.data.tables.UserEntity;
import de.blog.data.tables.UserRole;
import de.blog.data.tables.records.PostRecord;
import de.blog.data.tables.records.RoleRecord;
import de.blog.data.tables.records.SessionRecord;
import de.blog.data.tables.records.UserEntityRecord;
import de.blog.data.tables.records.UserRoleRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PostRecord> POST_PKEY = Internal.createUniqueKey(Post.POST, DSL.name("post_pkey"), new TableField[] { Post.POST.ID }, true);
    public static final UniqueKey<RoleRecord> ROLE_NAME_KEY = Internal.createUniqueKey(Role.ROLE, DSL.name("role_name_key"), new TableField[] { Role.ROLE.NAME }, true);
    public static final UniqueKey<RoleRecord> ROLE_PKEY = Internal.createUniqueKey(Role.ROLE, DSL.name("role_pkey"), new TableField[] { Role.ROLE.ID }, true);
    public static final UniqueKey<SessionRecord> SESSION_PKEY = Internal.createUniqueKey(Session.SESSION, DSL.name("session_pkey"), new TableField[] { Session.SESSION.ID }, true);
    public static final UniqueKey<SessionRecord> SESSION_TOKEN_KEY = Internal.createUniqueKey(Session.SESSION, DSL.name("session_token_key"), new TableField[] { Session.SESSION.TOKEN }, true);
    public static final UniqueKey<UserEntityRecord> USER_ENTITY_EMAIL_KEY = Internal.createUniqueKey(UserEntity.USER_ENTITY, DSL.name("user_entity_email_key"), new TableField[] { UserEntity.USER_ENTITY.EMAIL }, true);
    public static final UniqueKey<UserEntityRecord> USER_ENTITY_PKEY = Internal.createUniqueKey(UserEntity.USER_ENTITY, DSL.name("user_entity_pkey"), new TableField[] { UserEntity.USER_ENTITY.ID }, true);
    public static final UniqueKey<UserEntityRecord> USER_ENTITY_USERNAME_KEY = Internal.createUniqueKey(UserEntity.USER_ENTITY, DSL.name("user_entity_username_key"), new TableField[] { UserEntity.USER_ENTITY.USERNAME }, true);
    public static final UniqueKey<UserRoleRecord> USER_ROLE_PKEY = Internal.createUniqueKey(UserRole.USER_ROLE, DSL.name("user_role_pkey"), new TableField[] { UserRole.USER_ROLE.USER_ID, UserRole.USER_ROLE.ROLE_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<PostRecord, UserEntityRecord> POST__POST_CREATED_BY_FKEY = Internal.createForeignKey(Post.POST, DSL.name("post_created_by_fkey"), new TableField[] { Post.POST.CREATED_BY }, Keys.USER_ENTITY_PKEY, new TableField[] { UserEntity.USER_ENTITY.ID }, true);
    public static final ForeignKey<PostRecord, UserEntityRecord> POST__POST_UPDATED_BY_FKEY = Internal.createForeignKey(Post.POST, DSL.name("post_updated_by_fkey"), new TableField[] { Post.POST.UPDATED_BY }, Keys.USER_ENTITY_PKEY, new TableField[] { UserEntity.USER_ENTITY.ID }, true);
    public static final ForeignKey<RoleRecord, UserEntityRecord> ROLE__ROLE_CREATED_BY_FKEY = Internal.createForeignKey(Role.ROLE, DSL.name("role_created_by_fkey"), new TableField[] { Role.ROLE.CREATED_BY }, Keys.USER_ENTITY_PKEY, new TableField[] { UserEntity.USER_ENTITY.ID }, true);
    public static final ForeignKey<RoleRecord, UserEntityRecord> ROLE__ROLE_UPDATED_BY_FKEY = Internal.createForeignKey(Role.ROLE, DSL.name("role_updated_by_fkey"), new TableField[] { Role.ROLE.UPDATED_BY }, Keys.USER_ENTITY_PKEY, new TableField[] { UserEntity.USER_ENTITY.ID }, true);
    public static final ForeignKey<SessionRecord, UserEntityRecord> SESSION__SESSION_USER_ID_FKEY = Internal.createForeignKey(Session.SESSION, DSL.name("session_user_id_fkey"), new TableField[] { Session.SESSION.USER_ID }, Keys.USER_ENTITY_PKEY, new TableField[] { UserEntity.USER_ENTITY.ID }, true);
    public static final ForeignKey<UserEntityRecord, UserEntityRecord> USER_ENTITY__USER_ENTITY_CREATED_BY_FKEY = Internal.createForeignKey(UserEntity.USER_ENTITY, DSL.name("user_entity_created_by_fkey"), new TableField[] { UserEntity.USER_ENTITY.CREATED_BY }, Keys.USER_ENTITY_PKEY, new TableField[] { UserEntity.USER_ENTITY.ID }, true);
    public static final ForeignKey<UserEntityRecord, UserEntityRecord> USER_ENTITY__USER_ENTITY_UPDATED_BY_FKEY = Internal.createForeignKey(UserEntity.USER_ENTITY, DSL.name("user_entity_updated_by_fkey"), new TableField[] { UserEntity.USER_ENTITY.UPDATED_BY }, Keys.USER_ENTITY_PKEY, new TableField[] { UserEntity.USER_ENTITY.ID }, true);
    public static final ForeignKey<UserRoleRecord, RoleRecord> USER_ROLE__USER_ROLE_ROLE_ID_FKEY = Internal.createForeignKey(UserRole.USER_ROLE, DSL.name("user_role_role_id_fkey"), new TableField[] { UserRole.USER_ROLE.ROLE_ID }, Keys.ROLE_PKEY, new TableField[] { Role.ROLE.ID }, true);
    public static final ForeignKey<UserRoleRecord, UserEntityRecord> USER_ROLE__USER_ROLE_USER_ID_FKEY = Internal.createForeignKey(UserRole.USER_ROLE, DSL.name("user_role_user_id_fkey"), new TableField[] { UserRole.USER_ROLE.USER_ID }, Keys.USER_ENTITY_PKEY, new TableField[] { UserEntity.USER_ENTITY.ID }, true);
}