drop table if exists authors;
create table authors
( id         bigint primary key
, firstname  varchar(255)
, middlename varchar(255)
, lastname   varchar(255));

drop table if exists genres;
create table genres
( id    bigint primary key
, name  varchar(255));

drop table if exists books;
create table books
( id          bigint primary key
, name        varchar(255)
, authorid    bigint
, genreid     bigint
, releasedate date);

