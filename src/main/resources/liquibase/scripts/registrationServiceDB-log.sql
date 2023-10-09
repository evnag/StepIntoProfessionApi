-- liquibase formatted sql

-- changeSet evnag:1
create table address
(
    id        serial  not null primary key,
    address varchar(100) not null,
    city varchar(100) not null,
    state  varchar(100) not null,
    zip varchar(10) not null
);

create table intern
(
    id        serial  not null primary key,
    first_name varchar(50) not null,
    middle_name varchar(50) not null,
    last_name  varchar(50) not null,
    phone_number varchar(50) not null,
    email varchar(50) not null,
    gender varchar(50),
    birthday date not null,
    internship text not null,
    address_id int not null
);

-- changeSet evnag:2
alter table intern
    add constraint fk_intern_address_id foreign key (address_id) references address (id);

-- changeSet evnag:3
alter table intern
        alter column birthday type timestamp;

-- changeSet evnag:4
create table mentor
(
    id        serial  not null primary key,
    first_name varchar(50) not null,
    middle_name varchar(50) not null,
    last_name  varchar(50) not null,
    phone_number varchar(50) not null,
    email varchar(50) not null,
    gender varchar(50),
    birthday date not null,
    internship text not null,
    address_id int not null,
    intern_id int
);

create table recruiter
(
    id        serial  not null primary key,
    first_name varchar(50) not null,
    middle_name varchar(50) not null,
    last_name  varchar(50) not null,
    phone_number varchar(50) not null,
    email varchar(50) not null,
    gender varchar(50),
    birthday date not null,
    internship text not null,
    address_id int not null,
    intern_id int
);

-- changeSet evnag:5
alter table mentor
        alter column birthday type timestamp;

alter table recruiter
        alter column birthday type timestamp;

-- changeSet evnag:6
alter table mentor
    add constraint fk_mentor_intern_id foreign key (intern_id) references intern (id);

alter table recruiter
    add constraint fk_recruiter_intern_id foreign key (intern_id) references recruiter (id);