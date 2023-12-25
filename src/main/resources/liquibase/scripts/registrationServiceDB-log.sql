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

-- changeSet evnag:8
alter table address
    drop column address,
    drop column state,
    add column region varchar(100),
    add column street varchar(100),
    add column building varchar(10),
    add column apartment varchar(10);

-- changeSet evnag:9
alter table address
    alter column region set not null,
    alter column street set not null,
    alter column building set not null,
    alter column apartment set not null;

 -- changeSet evnag:10
create table project
(
    id        uuid  not null primary key,
    season_number int not null,
    start_date timestamp not null
);

 -- changeSet evnag:11
alter table intern
    add column project_id uuid,
    add constraint fk_intern_project_id foreign key (project_id) references project(id);

alter table mentor
    add column project_id uuid,
    add constraint fk_mentor_project_id foreign key (project_id) references project(id);

alter table recruiter
    add column project_id uuid,
    add constraint fk_recruiter_project_id foreign key (project_id) references project(id);

 -- changeSet evnag:12
 alter table address
    add column country varchar(250);

 -- changeSet evnag:13
 alter table intern
    add column disability_group int,
    add column disability_type varchar(250),
    add column language_skill varchar(50),
    add column cv_path text,
    add column video_cv_path text,
    add column tilda_cv_path text;

-- changeSet evnag:14
alter table intern
        alter column birthday type date;

alter table mentor
        alter column birthday type date;

alter table recruiter
        alter column birthday type date;

-- changeSet evnag:15
alter table mentor
        add column company varchar(250);

alter table recruiter
        add column company varchar(250);

-- changeSet evnag:16
alter table intern
    drop constraint fk_intern_project_id,
    drop column project_id;

create table project_intern
(
    project_id uuid references project (id) on update cascade on delete cascade,
    intern_id uuid references intern (id) on update cascade,
    constraint project_intern_pkey primary key (project_id, intern_id)
);

-- changeSet evnag:17
alter table mentor
    drop constraint fk_mentor_project_id,
    drop column project_id;

create table project_mentor
(
    project_id uuid references project (id) on update cascade on delete cascade,
    mentor_id uuid references mentor (id) on update cascade,
    constraint project_mentor_pkey primary key (project_id, mentor_id)
);

alter table recruiter
    drop constraint fk_recruiter_project_id,
    drop column project_id;

create table project_recruiter
(
    project_id uuid references project (id) on update cascade on delete cascade,
    recruiter_id uuid references recruiter (id) on update cascade,
    constraint project_recruiter_pkey primary key (project_id, recruiter_id)
);

-- changeSet evnag:18
alter table project
    alter column start_date type date;

-- changeSet evnag:19
alter table project
    add column end_date date;

-- changeSet evnag:20
create table team
(
    id        uuid  not null primary key,
    intern_id uuid,
    mentor_id uuid,
    recruiter_id uuid,
    internship text,
    season_number int not null,
    constraint fk_team_intern_id foreign key (intern_id) references intern (id),
    constraint fk_team_mentor_id foreign key (mentor_id) references mentor (id),
    constraint fk_team_recruiter_id foreign key (recruiter_id) references recruiter (id)
);

-- changeSet evnag:21
alter table recruiter
    drop constraint fk_recruiter_intern_id,
    add constraint fk_recruiter_intern_id foreign key (intern_id) references intern (id);