create table roles(
	id serial primary key,
	name varchar(255)
);

create table rules(
	id serial primary key,
	name varchar(255)
);

create table users(
	id serial primary key,
	name varchar(255),
	role_id int references roles(id)
);

create table role_rules(
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);

create table category(
	id serial primary key,
	name varchar(255)
);

create table states(
	id serial primary key,
	in_process boolean
);

create table item(
	id serial primary key,
	context text,
	user_id int references users(id),
	category_id int references category(id),
	state_id int references states(id)
);

create table comment(
	id serial primary key,
	review text,
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);