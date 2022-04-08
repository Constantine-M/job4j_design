CREATE DATABASE car_mechanic
		WITH OWNER = Consta;

CREATE TABLE body(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE engine(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE transmission(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE car
(
    id serial PRIMARY KEY,
    name varchar(255),
	body_id int REFERENCES body(id),
	engine_id int REFERENCES engine(id),
	transmission_id int REFERENCES transmission(id)
);

INSERT INTO body (name)
VALUES
('стальной'),
('углеволоконный'),
('алюминиевый'),
('железный'),
('стеклянный');

INSERT INTO engine (name)
VALUES
('1,6л 4-цилиндровый'),
('V8'),
('V12'),
('паровой'),
('электродвигатель');

INSERT INTO transmission (name)
VALUES
('ручная КПП'),
('автомат'),
('вариатор'),
('полуручная'),
('роботизированная КПП');

INSERT INTO car
(
	name,
	body_id,
	engine_id,
	transmission_id
)
VALUES
('Хотрод', null, 3, 1),
('мини-Морган', 4, null, null),
('Электромобиль', 3, 5, null),
('Чайка ГАЗ-13', 1, 2, 2),
('Toyota AVENSIS', 3, 1, 3),
('Феррари', 2, 3, 5);

SELECT 
car.name AS "Машинка", 
b.name AS "Кузов", 
e.name AS "Двигатель", 
tr.name AS "Трансмиссия"
FROM car
LEFT JOIN body AS b
ON car.body_id = b.id
LEFT JOIN engine AS e
ON car.engine_id = e.id
LEFT JOIN transmission AS tr
ON car.transmission_id = tr.id;

SELECT  
b.name AS "Кузов", 
car.name AS "Машинки"
FROM car
RIGHT JOIN body AS b
ON car.body_id = b.id
WHERE body_id IS null;

SELECT  
e.name AS "Двигло", 
car.name AS "Машинки"
FROM car
RIGHT JOIN engine AS e
ON car.engine_id = e.id
WHERE engine_id IS null;

SELECT  
tr.name AS "Коробка передач", 
car.name AS "Машинки"
FROM car
RIGHT JOIN transmission AS tr
ON car.transmission_id = tr.id
WHERE transmission_id IS null;

/*Такой способ гораздо более наглядый*/
SELECT  
b.name AS "Кузов", 
count(c.name)
FROM car c
RIGHT JOIN body AS b
ON c.body_id = b.id
group by b.name;