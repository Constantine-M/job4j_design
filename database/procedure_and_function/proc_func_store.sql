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
-- Процедура удаления записи.

call insert_data('hlebushek', 'producer_1', 25, 270);
call insert_data('milk', 'producer_2', 4, 32);
call insert_data('whisky', 'producer_3', 5, 230);
call insert_data('cola', 'producer_3', 10, 54);

-- Вот так почему-то не работает.
-- Ругается: "ERROR: missing FROM-clause entry for table "products"".
-- Видимо я неверно сравниваю поле со значением аргумента на входе процедуры.
-- Либо так вообще не работает/нельзя делать. Примеров найти не удалось.
-- Хотел, чтобы сравнивались значения полей с аргументами, а потом принималось решение
-- об удалении записей.

create or replace procedure delete_data_procedure(d_count integer, d_id integer, d_price integer)
    language 'plpgsql'
as $$
BEGIN
    if triggers.products.count < d_count THEN
        delete from triggers.products where id = d_id;
    ELSE
        if triggers.products.price > d_price THEN
            delete from triggers.products where id = d_id;
        end if;
    end if;
END;
$$;

-- Вот так работает.

create or replace procedure delete_data_procedure(d_count integer, d_id integer, d_price integer)
    language 'plpgsql'
as $$
BEGIN
    if d_count < 5 THEN
        delete from triggers.products where id = d_id;
    end if;
    if d_price > 250 THEN
        delete from triggers.products where id = d_id;
    end if;
END;
$$;

call delete_data_procedure(5, 1, 270);

-- Функция удаления записи.
-- Пока что не понимаю смысла в IF, если можно удалить указав условие в блоке WHERE.
-- Скорее всего, такие конструкции нужны для более комплексных запросов.
-- С блоком ELSE должно быть всё понятнее. Так мы можем расставить приоритеты,
-- потому что если на вход подадим count=4 и name=hlebushek, то вернется все равно 4.
-- Если count=10 и name=hlebushek, то молоко все равно удалится. Молоко обречено..

create or replace function f_delete_data(d_count integer, d_name varchar, d_id integer)
    returns integer
    language 'plpgsql'
as
$$
declare
    result integer;
begin
    if d_count < 5 THEN
        select into result count from triggers.products where id = d_id;
        delete from triggers.products where id = d_id;
    else
        if d_name = 'hlebushek' THEN
            delete from triggers.products where id = d_id;
            select into result sum(count) from triggers.products;
        end if;
    end if;
    return result;
end;
$$;

-- Вызов функции.

select f_delete_data(4, 'cola', 2);