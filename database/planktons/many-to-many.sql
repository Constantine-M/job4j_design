create table bank(
	id serial primary key,
	name varchar(255),
);

create table people(
	id serial primary key,
	name varchar(255),
);

create table bank_people(
	id serial primary key,
	people_id references people(id),
	bank_id references bank(id)
);

insert into people (name) values ('Petya');
insert into people (name) values ('Vasya');
insert into people (name) values ('Consta');

insert into bank (name) values ('VTB');
insert into bank (name) values ('Sber');
insert into bank (name) values ('UBRR');

insert into bank_people (people_id, bank_id) values (1, 1);
insert into bank_people (people_id, bank_id) values (1, 2);
insert into bank_people (people_id, bank_id) values (2, 1);
insert into bank_people (people_id, bank_id) values (2, 2);
insert into bank_people (people_id, bank_id) values (2, 3);
insert into bank_people (people_id, bank_id) values (3, 2);
insert into bank_people (people_id, bank_id) values (3, 3);