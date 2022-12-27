create table users (
    id       bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

create table roles (
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

create table privileges
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

create table users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table users_privileges
(
    user_id bigint not null,
    privilege_id int not null,
    primary key (user_id, privilege_id),
    foreign key (user_id) references users (id),
    foreign key (privilege_id) references privileges (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into privileges (name)
values ('PRIVILEGE_READ'),
       ('PRIVILEGE_WRITE'),
       ('PRIVILEGE_DELETE');

insert into users (username, password, email)
values
    ('user', '$2a$12$tErxEXtfLTS0p4qDhNCkSOhB4F3YotHo/GQAlYgKxxSVuSc.O/w0u', 'user@gmail.com'),
    ('manager', '$2a$12$tErxEXtfLTS0p4qDhNCkSOhB4F3YotHo/GQAlYgKxxSVuSc.O/w0u', 'manager@gmail.com'),
    ('admin', '$2a$12$emVYrHukVDKKk44Fw8nrueSYdAM5SgXshia4m6GgJFfErl89xaAcW', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1), (2, 1), (2, 2), (3, 1), (3, 2);

insert into users_privileges (user_id, privilege_id)
values (1, 1), (2, 1), (2, 2), (3, 1), (3, 2), (3, 3);
