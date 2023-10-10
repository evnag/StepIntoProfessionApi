-- liquibase formatted sql

-- changeSet evnag:1
create table address
(
    id        uuid  not null primary key,
    address varchar(100) not null,
    city varchar(100) not null,
    state  varchar(100) not null,
    zip varchar(10) not null
);

create table intern
(
    id        uuid  not null primary key,
    first_name varchar(50) not null,
    middle_name varchar(50) not null,
    last_name  varchar(50) not null,
    phone_number varchar(50) not null,
    email varchar(50) not null,
    gender varchar(50),
    birthday date not null,
    internship text not null,
    address_id uuid not null
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
    id        uuid  not null primary key,
    first_name varchar(50) not null,
    middle_name varchar(50) not null,
    last_name  varchar(50) not null,
    phone_number varchar(50) not null,
    email varchar(50) not null,
    gender varchar(50),
    birthday date not null,
    internship text not null,
    address_id uuid not null,
    intern_id uuid
);

create table recruiter
(
    id        uuid  not null primary key,
    first_name varchar(50) not null,
    middle_name varchar(50) not null,
    last_name  varchar(50) not null,
    phone_number varchar(50) not null,
    email varchar(50) not null,
    gender varchar(50),
    birthday date not null,
    internship text not null,
    address_id uuid not null,
    intern_id uuid
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

-- changeSet evnag:7
alter table intern
    drop column first_name;
alter table intern
    drop column middle_name;
alter table intern
    drop column last_name;
alter table intern
    add column full_name varchar(250);

alter table mentor
    drop column first_name;
alter table mentor
    drop column middle_name;
alter table mentor
    drop column last_name;
alter table mentor
    add column full_name varchar(250);

alter table recruiter
    drop column first_name;
alter table recruiter
    drop column middle_name;
alter table recruiter
    drop column last_name;
alter table recruiter
    add column full_name varchar(250);




