create table yachttype
(
id serial primary key,
name varchar(255)
);

create table yachts
(
id serial primary key,
name varchar(255),
lengths numeric,
engine boolean,
yachttype_id int references yachttype(id)
);

insert into yachttype (name) values ('парусная');
insert into yachttype (name) values ('моторная');
insert into yachttype (name) values ('круизная');
insert into yachttype (name) values ('спортивная');
insert into yachttype (name) values ('прогулочная');
insert into yachttype (name) values ('суперяхта');
insert into yachttype (name) values ('мегаяхта');

insert into yachts (name, lengths, engine, yachttype_id)
values ('Oceanis 34.1', 11, false, 1);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Oceanis 34.1', 11, false, 3);
insert into yachts (name, lengths, engine, yachttype_id)
values ('First 27', 8, false, 1);
insert into yachts (name, lengths, engine, yachttype_id)
values ('First 27', 8, false, 4);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Yacht 62', 20, false, 1);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Velvette 23', 7, true, 2);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Velvette 23', 7, true, 5);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Velvette 29 Envy', 9, true, 2);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Velvette 29 Envy', 9, true, 3);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Velvette 29 Envy', 9, true, 4);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Galeon 780 Crystal', 25, true, 2);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Galeon 780 Crystal', 25, true, 6);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Wally Ace 42', 42, true, 2);
insert into yachts (name, lengths, engine, yachttype_id)
values ('Wally Ace 42', 42, true, 7);

select yach.name, yatype.name
from yachts as yach
join yachttype as yatype
on yach.yachttype_id = yatype.id

select yach.name, yatype.name, yach.lengths
from yachts as yach
join yachttype as yatype
on yach.lengths > 12

select yach.name, yatype.name, yach.engine, yach.yachttype_id
from yachts as yach
join yachttype as yatype
on yach.yachttype_id = 7