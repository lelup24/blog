insert into user_entity (id, username, password, salt)
values ('08cf55be-7003-4eb2-89b9-f54d680ddcc5', 'peter',
        '$2a$12$MkTGiGdb4wZT6J/3DuXET.yh.wg4ED.ge7oBQNTaNp9l0XLuKcTk.', '08cf55be70034eb289b9f54d680ddcc5');

insert into role (id, name)
values ('08cf55be-7003-4eb2-89b9-f54d680ddcc5', 'ADMIN');

insert into user_role (user_id, role_id)
values ('08cf55be-7003-4eb2-89b9-f54d680ddcc5', '08cf55be-7003-4eb2-89b9-f54d680ddcc5');
