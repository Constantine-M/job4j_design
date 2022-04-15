create table car(
	id serial primary key,
	model varchar(255),
	year numeric
);

create table vin_code(
	id serial primary key,
	code char(17),
);

create table vin_cars(
	id serial primary key,
	car_id references car(id) unique,
	vin_code_id references vin_code(id) unique
);