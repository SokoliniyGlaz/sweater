insert into usr (id, username, password, active,email)
values (1, 'admin', 'admin', true,'jana_sokolova@mail.ru');

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');