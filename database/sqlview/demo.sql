CREATE SCHEMA sqlview;

create table sqlview.students (
                                  id serial primary key,
                                  name varchar(50)
);

insert into sqlview.students (name) values ('Иван Иванов');
insert into sqlview.students (name) values ('Петр Петров');

create table sqlview.authors (
                                 id serial primary key,
                                 name varchar(50)
);

insert into sqlview.authors (name) values ('Александр Пушкин');
insert into sqlview.authors (name) values ('Николай Гоголь');

create table sqlview.books (
                               id serial primary key,
                               name varchar(200),
                               author_id integer references sqlview.authors(id)
);

insert into sqlview.books (name, author_id) values ('Евгений Онегин', 1);
insert into sqlview.books (name, author_id) values ('Капитанская дочка', 1);
insert into sqlview.books (name, author_id) values ('Дубровский', 1);
insert into sqlview.books (name, author_id) values ('Мертвые души', 2);
insert into sqlview.books (name, author_id) values ('Вий', 2);

create table sqlview.orders (
                                id serial primary key,
                                active boolean default true,
                                book_id integer references sqlview.books(id),
                                student_id integer references sqlview.students(id)
);

insert into sqlview.orders (book_id, student_id) values (1, 1);
insert into sqlview.orders (book_id, student_id) values (3, 1);
insert into sqlview.orders (book_id, student_id) values (5, 2);
insert into sqlview.orders (book_id, student_id) values (4, 1);
insert into sqlview.orders (book_id, student_id) values (2, 2);

select s.name, count(a.name), a.name from sqlview.students as s
                                              join sqlview.orders o on s.id = o.student_id
                                              join sqlview.books b on o.book_id = b.id
                                              join sqlview.authors a on b.author_id = a.id
group by (s.name, a.name) having count(a.name) >= 2;