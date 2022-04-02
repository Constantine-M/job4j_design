CREATE DATABASE electronic
		WITH OWNER = Consta

CREATE table devices(
    id serial primary key,
    name varchar(255),
    price float
);

CREATE table people(
    id serial primary key,
    name varchar(255)
);

CREATE table devices_people(
    id serial primary key,
    device_id int REFERENCES devices(id),
    people_id int REFERENCES people(id)
);

INSERT INTO people (name)
VALUES ('Добрыня');
INSERT INTO people (name)
VALUES ('Consta');
INSERT INTO people (name)
VALUES ('John');
INSERT INTO people (name)
VALUES ('Sherlock');
INSERT INTO people (name)
VALUES ('Вася');
INSERT INTO people (name)
VALUES ('Ольга');

INSERT INTO devices (name, price)
VALUES ('Notebook', 186890.99);
INSERT INTO devices (name, price)
VALUES ('Phone', 38990.45);
INSERT INTO devices (name, price)
VALUES ('Earbuds', 4370.15);
INSERT INTO devices (name, price)
VALUES ('Watch', 25520.99);
INSERT INTO devices (name, price)
VALUES ('Tablet', 47990.99);
INSERT INTO devices (name, price)
VALUES ('Action camera', 2569.21);
INSERT INTO devices (name, price)
VALUES ('Quadcopter', 131999.99);

INSERT INTO devices_people (people_id, device_id)
VALUES (1, 7);
INSERT INTO devices_people (people_id, device_id)
VALUES (1, 3);
INSERT INTO devices_people (people_id, device_id)
VALUES (2, 1);
INSERT INTO devices_people (people_id, device_id)
VALUES (2, 3);
INSERT INTO devices_people (people_id, device_id)
VALUES (2, 4);
INSERT INTO devices_people (people_id, device_id)
VALUES (3, 4);
INSERT INTO devices_people (people_id, device_id)
VALUES (3, 6);
INSERT INTO devices_people (people_id, device_id)
VALUES (4, 1);
INSERT INTO devices_people (people_id, device_id)
VALUES (4, 2);
INSERT INTO devices_people (people_id, device_id)
VALUES (5, 3);
INSERT INTO devices_people (people_id, device_id)
VALUES (5, 5);
INSERT INTO devices_people (people_id, device_id)
VALUES (6, 1);
INSERT INTO devices_people (people_id, device_id)
VALUES (6, 6);
INSERT INTO devices_people (people_id, device_id)
VALUES (6, 7);

SELECT AVG(d.price) AS "Average price"
FROM devices AS d;

SELECT pipl.name AS "Людишки",
AVG(d.price) AS "Средняя цена устройств человека"
FROM devices_people AS dp
JOIN people AS pipl
ON dp.people_id = pipl.id
JOIN devices AS d
ON dp.device_id = d.id
GROUP BY pipl.name;

SELECT pipl.name AS "Людишки",
AVG(d.price) AS "Средняя цена устройств человека"
FROM devices_people AS dp
JOIN people AS pipl
ON dp.people_id = pipl.id
JOIN devices AS d
ON dp.device_id = d.id
GROUP BY pipl.name
HAVING AVG(d.price) > 5000;