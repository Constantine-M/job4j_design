drop table triggers.products;

create table products (
                          id serial primary key,
                          name varchar(50),
                          producer varchar(50),
                          count integer default 0,
                          price integer
);

-- Here's the stored procedure.
-- Это пример хранимой процедуры.

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    language 'plpgsql'
as $$
BEGIN
    insert into triggers.products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
END
$$;

-- Inserting data using a procedure. You need to "CALL" procedure.
-- Чтобы использовать процедуру, нужно использовать CALL и передать входящие параметры.

call insert_data('product_2', 'producer_2', 15, 32);

-- Update data procedure in our table.
-- Пример обновления данных в таблице. На вход принимаем 3 параметра.

create or replace procedure update_data(u_count integer, tax float, u_id integer)
    language 'plpgsql'
as $$
BEGIN
    if u_count > 0 THEN
        update triggers.products set count = count - u_count where id = u_id;
    end if;
    if tax > 0 THEN
        update triggers.products set price = price + price * tax;
    end if;
END;
$$;

-- Call the update data procedure.

call update_data(10, 0, 1);

-- You can update procedure (for ex. rename etc.) with the ALTER PROCEDURE command.
-- Обновить процедуру (например, переименовать) как и обычно можно с помощью ALTER PROCEDURE.

alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;

-- Delete procedure with the DROP command.

drop procedure update_data(u_count integer, tax float, u_id integer);

-- Restart sequence with 1 again.
-- Сбросить счетчик до 1. Если счетчик id был 26 например, то данная команда сбросит до 1.
ALTER SEQUENCE triggers.products_id_seq RESTART WITH 1;

-- Here's example of stored function.
-- Ниже пример хранимой процедуры. Обрати внимание, что появилась строка с return...

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    returns void
    language 'plpgsql'
as
$$
begin
    insert into triggers.products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
end;
$$;

-- You can call function with the SELECT command.
-- Функции вызываются через обычный SELECT.

select f_insert_data('product_1', 'producer_1', 25, 50);

-- Refactor our procedure into the function.
-- Перепишем ранее созданную процедуру для редактирования записей в таблице на функцию.
-- При этом она будет с возвращаемым значением.
-- С помощью declare мы объявили переменную result.

create or replace function f_update_data(u_count integer, tax float, u_id integer)
    returns integer
    language 'plpgsql'
as
$$
declare
    result integer;
begin
    if u_count > 0 THEN
        update triggers.products set count = count - u_count where id = u_id;
        select into result count from triggers.products where id = u_id;
    end if;
    if tax > 0 THEN
        update triggers.products set price = price + price * tax;
        select into result sum(price) from triggers.products;
    end if;
    return result;
end;
$$;

-- Calling the function we're written above.
-- Вызовем написанную выше функцию.

select f_update_data(10, 0, 1);

-- Скрипты по задаче.
-- Заполним таблицу произвольными данными.

delete from triggers.products;
ALTER SEQUENCE triggers.products_id_seq RESTART WITH 1;

call insert_data('hlebushek', 'commodity', 25, 270);
call insert_data('milk', 'commodity', 4, 32);
call insert_data('whisky', 'alcohol', 15, 230);
call insert_data('wine', 'alcohol', 10, 54);

-- Процедура удаления записи.
-- Данная процедура удаляет алкоголь в праздничные дни, иначе удаляет товары повседневного спроса.

create or replace procedure delete_data_procedure(feast boolean)
    language 'plpgsql'
as $$
BEGIN
    if feast is true THEN
        delete from triggers.products where producer = 'alcohol';
    else
        delete from triggers.products where producer = 'commodity';
    end if;
END;
$$;

call delete_data_procedure(true);

-- Функция удаления записи.
-- Данная функция удаляет только товары ежедневного спроса (commodity) дороже 100.
-- Возвращает кол-во удаленных товаров.

create or replace function f_delete_data(d_price integer)
    returns integer
    language 'plpgsql'
as
$$
declare
    result integer;
    pp triggers.products%rowtype;
begin
    select into result sum(count) from triggers.products;
    for pp in (select * from triggers.products where producer = 'commodity')
        loop
            if pp.price > d_price then
                delete from triggers.products where id = pp.id;
            end if;
        end loop;
    result = result - (select sum(count) from triggers.products);
    return result;
end
$$;

-- Вызов функции.

select f_delete_data(100);
