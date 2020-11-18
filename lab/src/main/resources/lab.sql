/*
* sql for lab
* created by qdl in 2020/10/25
*/
create table user
(
    username varchar(10)                 not null
        primary key,
    pass     varchar(8) default '888888' not null
);

create table person
(
    username varchar(10) not null,
    name     varchar(20) not null
        primary key,
    age      int         null,
    teleno   char(11)    null,
    constraint person_user_username_fk
        foreign key (username) references user (username)
)
    comment '人物表';

