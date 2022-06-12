insert into roles (role_id, role)
values (1, 'ADMIN');
insert into roles (role_id, role)
values (2, 'MANAGER');
insert into roles (role_id, role)
values (3, 'USER');

alter sequence role_seq restart with 4;

insert into users (user_id, mail, password, role_id,archive)
values (1,'1mail@bk.ru',12345678,1,false);

alter sequence user_seq restart with 2;
