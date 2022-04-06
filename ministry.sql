CREATE DATABASE ministry
		WITH OWNER = Consta

CREATE TABLE departments(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE employees(
    id serial PRIMARY KEY,
    name varchar(255),
	department_id int REFERENCES departments(id)
);

CREATE TABLE teens(
    id serial PRIMARY KEY,
    name varchar(255),
	gender varchar(255)
);

INSERT INTO departments(name)
VALUES
('Отдел обеспечения магического правопорядка'),
('Отдел магических происшествий и катастроф'),
('Отдел магического транспорта'),
('Отдел тайн'),
('Залы суда');

INSERT INTO employees(name, department_id)
VALUES
('Consta', 1),
('Consta', 5),
('Olga', 2),
('Vasya', 3),
('Lucius', 5),
('Hagrid', 3),
('Luna', 2);

INSERT INTO teens(name, gender)
VALUES
('Consta', 'мужик'),
('Marusya', 'демигендер'),
('Eddie', 'цисгендер'),
('Olga', 'гендер-флюид'),
('Zhenya', 'женщина'),
('Valera', 'бигендер');

SELECT * FROM employees e
LEFT JOIN departments d
ON e.department_id = d.id;

SELECT * FROM employees e
RIGHT JOIN departments d
ON e.department_id = d.id;

SELECT * FROM employees e
FULL JOIN departments d
ON e.department_id = d.id;

SELECT * FROM employees e
CROSS JOIN departments d;

SELECT * FROM departments d
LEFT JOIN employees e
ON e.department_id = d.id
WHERE e.department_id is null;

SELECT * FROM departments d
RIGHT JOIN employees e
ON e.department_id = d.id;
SELECT * FROM departments d
LEFT JOIN employees e
ON e.department_id = d.id
WHERE e.department_id IS NOT null;

SELECT n.name, g.gender
FROM teens n
CROSS JOIN teens g;

SELECT n.name AS "Имя", 
g.gender AS "Гендерный идентификатор",
CONCAT_WS(' - ', n.name, g.gender) AS "Декартово множество"
FROM teens n
CROSS JOIN teens g;