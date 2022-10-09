drop table triggers.products;

create table triggers.products (
                                   id serial primary key,
                                   name varchar(50),
                                   count integer default 0,
                                   price integer
);

create or replace procedure insert_data(i_name varchar, i_count integer, i_price integer)
    language 'plpgsql'
as $$
BEGIN
    insert into triggers.products (name, count, price)
    VALUES (i_name, i_count, i_price);
END
$$;

call insert_data('product_1', 1, 5);
call insert_data('product_2', 2, 10);
call insert_data('product_3', 3, 15);
call insert_data('product_4', 4, 20);
call insert_data('product_5', 5, 25);
call insert_data('product_6', 6, 30);
call insert_data('product_7',7, 35);
call insert_data('product_8', 8, 40);
call insert_data('product_9', 9, 45);
call insert_data('product_10', 10, 50);
call insert_data('product_11', 11, 55);
call insert_data('product_12', 12, 60);
call insert_data('product_13', 13, 65);
call insert_data('product_14', 14, 70);
call insert_data('product_15', 15, 75);
call insert_data('product_16', 16, 80);
call insert_data('product_17', 17, 85);
call insert_data('product_18', 18, 90);
call insert_data('product_19', 19, 95);
call insert_data('product_20', 20, 100);

-- Объявление курсора.

start transaction;
declare
    cursor_products cursor for select * from triggers.products;

-- Прочитать первые 10 записей.

FETCH 10 FROM cursor_products;

-- Прочитать еще пару строк с направлением NEXT.

FETCH FROM cursor_products;
FETCH FROM cursor_products;

-- Переместить курсор в новую позицию с помощью MOVE команды.
-- Переместим курсор на 2 строки вперед.

MOVE FORWARD 2 FROM cursor_products;

-- Закрытие курсора – простой, но важный процесс.
-- Закроем наш курсор.

CLOSE cursor_products;

-- Скрипты по задаче.
-- Нужно пройти по списку в обратном направлении.

-- Объявление курсора.

start transaction;
declare
    cursor_products SCROLL cursor for select * from triggers.products;

-- Переход на последнюю запись.

MOVE FORWARD 19 FROM cursor_products;
FETCH FROM cursor_products;

-- Прочитать первые 10 записей с конца.

FETCH BACKWARD 10 FROM cursor_products;

-- Прочитать еще пару записей в направлении BACKWARD.

FETCH BACKWARD FROM cursor_products;
FETCH BACKWARD FROM cursor_products;

-- Переместить курсор в  обратном направлении.

MOVE BACKWARD 5 FROM cursor_products;
FETCH BACKWARD FROM cursor_products;

-- Закроем курсор и закомитим (закроем) транзакцию.

CLOSE cursor_products;
COMMIT;

delete from triggers.products;
ALTER SEQUENCE triggers.products_id_seq RESTART WITH 1;
select * from triggers.products;