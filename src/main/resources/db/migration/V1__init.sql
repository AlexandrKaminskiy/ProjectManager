Hibernate: alter table if exists project_info_author drop constraint if exists FKdd3q1qvbmjap760t6ugtkl2se
    Hibernate: alter table if exists project_info_author drop constraint if exists FKb7bffhwffhn8la4q2a5o8m7yn
    Hibernate: alter table if exists task_info drop constraint if exists FKa0avh51bcp74mj7gmdp9l5dxk
    Hibernate: alter table if exists user_role drop constraint if exists FKfpm8swft53ulq2hl11yplpr5
    Hibernate: alter table if exists usr_project drop constraint if exists FKg7ptgi0euu8q4cuhc5ac5l33j
    Hibernate: alter table if exists usr_project drop constraint if exists FK14fsraay0muj1d5x3cp7ktj16
    Hibernate: drop table if exists project_info cascade
    Hibernate: drop table if exists project_info_author cascade
    Hibernate: drop table if exists task_info cascade
    Hibernate: drop table if exists user_role cascade
    Hibernate: drop table if exists usr cascade
    Hibernate: drop table if exists usr_project cascade
    Hibernate: drop sequence if exists hibernate_sequence
    Hibernate: create sequence hibernate_sequence start 1 increment 1
Hibernate: create table project_info (id int8 not null, company_name varchar(255), is_deleted boolean, is_ready boolean, name varchar(255), primary key (id))
    Hibernate: create table project_info_author (project_info_id int8 not null, author_id int8 not null)
    Hibernate: create table task_info (id int8 not null, is_deleted boolean, task varchar(255), project_id int8, primary key (id))
    Hibernate: create table user_role (user_id int8 not null, roles varchar(255))
    Hibernate: create table usr (id int8 not null, active boolean, password varchar(255), username varchar(255), primary key (id))
    Hibernate: create table usr_project (user_id int8 not null, project_id int8 not null)
    Hibernate: alter table if exists project_info_author add constraint FKdd3q1qvbmjap760t6ugtkl2se foreign key (author_id) references usr
    Hibernate: alter table if exists project_info_author add constraint FKb7bffhwffhn8la4q2a5o8m7yn foreign key (project_info_id) references project_info
    Hibernate: alter table if exists task_info add constraint FKa0avh51bcp74mj7gmdp9l5dxk foreign key (project_id) references project_info
    Hibernate: alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr
    Hibernate: alter table if exists usr_project add constraint FKg7ptgi0euu8q4cuhc5ac5l33j foreign key (project_id) references project_info
    Hibernate: alter table if exists usr_project add constraint FK14fsraay0muj1d5x3cp7ktj16 foreign key (user_id) references usr