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
    created_at     timestamp,
    updated_at     timestamp,
    primary key (id),
    foreign key (user_id) references user_entity (id)
);

create table token
(
    id         uuid,
    session_id uuid,
    token      text unique,
    expires_at timestamp,
    created_at timestamp,
    primary key (id),
    foreign key (session_id) references session (id)
);
