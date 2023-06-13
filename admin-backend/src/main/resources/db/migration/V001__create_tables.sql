create table user_entity
(
    id             uuid,
    username       varchar(255) unique,
    password       varchar(255),
    salt           varchar(255) not null,
    email          varchar(255) unique,
    email_verified boolean,
    firstname      varchar(255),
    lastname       varchar(255),
    created_at     timestamp,
    created_by     uuid,
    updated_at     timestamp,
    updated_by     uuid,
    primary key (id),
    foreign key (created_by) references user_entity (id),
    foreign key (updated_by) references user_entity (id)
);

create table role
(
    id         uuid,
    name       varchar(255) unique,
    created_at timestamp,
    created_by uuid,
    updated_at timestamp,
    updated_by uuid,
    primary key (id),
    foreign key (created_by) references user_entity (id),
    foreign key (updated_by) references user_entity (id)
);

create table user_role
(
    user_id uuid,
    role_id uuid,
    primary key (user_id, role_id),
    foreign key (user_id) references user_entity (id),
    foreign key (role_id) references role (id)
);

create table post
(
    id         uuid,
    title      varchar(255),
    content    text,
    created_at timestamp,
    created_by uuid,
    updated_at timestamp,
    updated_by uuid,
    primary key (id),
    foreign key (created_by) references user_entity (id),
    foreign key (updated_by) references user_entity (id)
);

create table session
(
    id             uuid,
    user_id        uuid,
    remote_address varchar(255),
    revoked        boolean,
    token          text unique,
    expires_at     timestamp,
    created_at     timestamp,
    updated_at     timestamp,
    primary key (id),
    foreign key (user_id) references user_entity (id)
);

create table user_session
(
    primary_id            char(36) not null,
    session_id            char(36) not null,
    creation_time         bigint   not null,
    last_access_time      bigint   not null,
    max_inactive_interval int      not null,
    expiry_time           bigint   not null,
    principal_name        varchar(100),
    constraint spring_session_pk primary key (primary_id)
);

create unique index spring_session_ix_session_id on user_session (session_id);
create index spring_session_ix_expiry_time on user_session (expiry_time);
create index spring_session_ix_principal_name on user_session (principal_name);



CREATE TABLE user_session_attributes
(
    SESSION_PRIMARY_ID CHAR(36)     NOT NULL,
    ATTRIBUTE_NAME     VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES    BYTEA        NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES user_session (PRIMARY_ID) ON DELETE CASCADE
);
