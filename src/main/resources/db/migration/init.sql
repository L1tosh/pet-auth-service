create table users
(
    id       bigserial,
    name     varchar(48) not null,
    surname  varchar(48) not null,
    password varchar(48) not null,
    email    varchar(64) not null unique,

    primary key (id)
);