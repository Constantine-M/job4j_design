create table transaction.regime (
                                    id serial primary key,
                                    name varchar(50),
                                    time timestamp,
                                    description text
);

create or replace procedure ins_data(i_name varchar, i_time timestamp, i_description text)
    language 'plpgsql'
as $$
BEGIN
    insert into transaction.regime(name , time, description)
    values (i_name, i_time, i_description);
END
$$;

-- Самые основные моменты режима дня.

call ins_data('Early alarm', '2022-10-07 06:30', 'Set early alarm to be more productive.');
call ins_data('Coffee time', '2022-10-07 06:31', 'More coffee - more productive!');
call ins_data('Save the world', '2022-10-07 10:00', 'Get a kitten out of a tree.');
call ins_data('Breakfast', '2022-10-07 13:00', '...and then steak and lobster for the main part.');
call ins_data('Dinner', '2022-10-07 20:00', 'Light supper - lamb on the spit');

call ins_data('Daily rest', '2022-10-07 15:00', 'Take a power nap');

-- Эти команды будут запущены во время транзакции.

call ins_data('Work?', '2022-10-07 16:00', 'work????');
delete from transaction.regime where name = 'Save the world';
update transaction.regime set time = '2022-10-07 09:30' where name = 'Early alarm';
update transaction.regime set time = '2022-10-07 09:31' where name = 'Coffee time';

select * from transaction.regime order by time;

delete from transaction.regime;
ALTER SEQUENCE transaction.regime_id_seq RESTART WITH 1;