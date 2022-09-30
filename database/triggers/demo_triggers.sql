create schema triggers;

create table triggers.products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function discount()
    returns trigger as
$$
BEGIN
    update triggers.products
    set price = price - price * 0.2
    where count <= 5 AND id = new.id;
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on triggers.products
    for each row
execute procedure discount();

insert into triggers.products (name, producer, count, price)
    VALUES ('product_1', 'producer_1', 1, 10);
insert into triggers.products (name, producer, count, price)
    VALUES ('product_2', 'producer_2', 2, 20);
insert into triggers.products (name, producer, count, price)
    VALUES ('product_3', 'producer_3', 3, 30);

alter table triggers.products disable trigger discount_trigger;

drop trigger discount_trigger on triggers.products;

create or replace function tax()
    returns trigger as
$$
BEGIN
    update triggers.products
    set price = price - price * 0.2
    where id = (select id from inserted) and count <= 5;
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on triggers.products
    referencing new table as inserted
    for each statement
execute procedure tax();

create or replace function new_tax()
    returns trigger as
$$
BEGIN
    update triggers.products
    set price = price + price * 0.2;
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger new_tax_trigger
    after insert on triggers.products
    referencing new table as inserted
    for each statement
execute procedure new_tax();

create trigger tax_trigger_before_insert
    before insert
    on triggers.products
    for each row
execute procedure new_tax();

create table triggers.history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history()
    returns trigger as
$$
BEGIN
    insert into triggers.history_of_price
    (id, name, price, date) VALUES (new.id, new.name, new.price, now());
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger price_history_trigger
    after insert
    on triggers.products
    for each row
execute procedure history();
