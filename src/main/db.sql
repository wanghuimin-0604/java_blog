drop databases if  exists java_blog;

create database java_blog;

use database java_blog;
drop table if exists User;

create table User(
userId int primary key auto_increment,
name varchar(20) unique,
password varchar(20)
);

drop table if exists Article;
create table Article (
    articleId int primary key auto_increment,
    title varchar(255),
    content text,
    userId int,
    foreign key(userId) references user(userId)
);
