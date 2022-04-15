create table address(
	id serial primary key,
	street varchar(255),
	house numeric
);

create table folks(
	id serial primary key,
	name varchar(255),
	address_id int references address(id)
);

insert into address (street, house) values ('Lenina', 365);
insert into folks (name, address_id) values ('Consta', 1);

select * from folks;

select * from address where id in (select id from folks);