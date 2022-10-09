create schema index;

create table index.people(
                       id serial primary key,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       phone VARCHAR(50)
);

-- Создадим индекс для нашей таблицы для столбца last_name и укажем что сортировка будет по убыванию.

create index people_last_name on index.people(last_name desc);

-- Чтобы изменить индекс, воспользуемся командой ALTER.
-- Например, переименуем индекс.

alter index people_last_name rename to people_last_name_desc;

-- Чтобы удалить индекс.

drop index people_last_name;

delete from index.people;
alter sequence index.people_id_seq RESTART WITH 1;
select * from index.people;