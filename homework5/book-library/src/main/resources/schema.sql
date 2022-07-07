drop table if exists authors;
create table authors
( id         bigint auto_increment primary key
, firstname  varchar(255)
, middlename varchar(255)
, lastname   varchar(255));

drop table if exists genres;
create table genres
( id    bigint auto_increment primary key
, name  varchar(255));

drop table if exists books;
create table books
( id          bigint auto_increment primary key
, name        varchar(255)
, authorid    bigint
, genreid     bigint
, releasedate date
, constraint authors_fk1 foreign key (authorid) references authors(id)
, constraint authors_fk2 foreign key (genreid) references genres(id));

