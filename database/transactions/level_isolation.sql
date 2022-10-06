create schema transaction;

-- Создал таблицу, которая будет отражать фильмы с рейтингом 0% на Rotten Tomatoes.
-- Антирейтинг/самые некассовые/непопулярные/нелюбимые фильмы в общем.
-- Так как синтаксис местами разный, то для psql использую скрипты ниже (для удобства).

create table transaction.rating (
    id serial primary key,
    name varchar(50),
    year integer default 0,
    reviews integer
);

create or replace procedure insert_data(i_name varchar, i_year integer, i_reviews integer)
    language 'plpgsql'
as $$
BEGIN
    insert into transaction.rating (name, year, reviews)
    values (i_name, i_year, i_reviews);
END
$$;

call insert_data('Bolero', 1984, 23);
call insert_data('Redline', 2007, 27);
call insert_data('Hard Kill', 2020, 21);

select sum(reviews) from transaction.rating;

call insert_data('Killing Me Softly', 2002, 23);
delete from transaction.rating where year = 2007;
update transaction.rating set reviews = 24 where name = 'Bolero';
update transaction.rating set reviews = 28 where name = 'Redline';

delete from transaction.rating;
ALTER SEQUENCE transaction.rating_id_seq RESTART WITH 1;

-- Для экспериментов с MySQL использую эти скрипты. Так же, для удобства.

create table rating (
    id serial primary key,
    name varchar(50),
    year integer default 0,
    reviews integer
);

insert into rating(name, year, reviews) values ('Bolero', 1984, 23);
insert into rating(name, year, reviews) values ('Redline', 2007, 27);
insert into rating(name, year, reviews) values ('Hard Kill', 2020, 21);

set session transaction isolation level read uncommitted;
start transaction;

insert into rating(name, year, reviews) values ('Killing Me Softly', 2002, 23);
delete from rating where year = 2007;
update rating set reviews = 24 where name = 'Bolero';

select sum(reviews) from rating;