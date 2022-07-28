insert into authors (firstname, middlename, lastname)
values ('Mario', 'Gianluigi', 'Puzo');

insert into genres (name)
values('Novel');

insert into books(name, authorid, genreid)
values('The Godfather', 1, 1);

insert into comments(bookid, text)
values(1, 'Text');