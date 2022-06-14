insert into roles (role_id, role)
values (1, 'USER');
insert into roles (role_id, role)
values (2, 'MANAGER');
insert into roles (role_id, role)
values (3, 'ADMIN');

alter sequence role_seq restart with 4;

insert into users (user_id, mail, password, role_id,archive)
values (1,'1mail@bk.ru','$2a$10$WzU8czw8Lo2pHELHAL5mtOgxV8oZ72eKeyrxOsNqMrEW/qPVj8qi.',1,false);

alter sequence user_seq restart with 2;
