insert into authors (id, firstname, middlename, lastname)
values (1, 'Mario', 'Gianluigi', 'Puzo');

insert into genres (id, name)
values(1, 'Novel');

insert into books(id, name, authorid, genreid, releasedate)
values(1, 'The Godfather', 1, 1, '1969-03-10');