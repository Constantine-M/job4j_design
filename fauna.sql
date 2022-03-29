create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values('Lactobacillus acidophilus', 0.05, '1920-12-01');
insert into fauna(name, avg_age, discovery_date)
values('nofish', 54750, '1958-02-05');
insert into fauna(name, avg_age, discovery_date)
values('человек', 23725, null);
insert into fauna(name, avg_age, discovery_date)
values('коза', 7300, null);
insert into fauna(name, avg_age, discovery_date)
values('кит', 73000, null);
insert into fauna(name, avg_age, discovery_date)
values('нанолягушка', 8485, '2009-09-09');
insert into fauna(name, avg_age, discovery_date)
values('сумчатый волк', 45871, '1808-06-03');
insert into fauna(name, avg_age, discovery_date)
values('Антильский хомяк', 1246, '1784-03-21');
insert into fauna(name, avg_age, discovery_date)
values('кот', 11000, '1944-01-21');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 AND avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';