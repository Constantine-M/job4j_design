CREATE DATABASE grocer
		WITH OWNER = Consta

CREATE TABLE prodtype(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE product(
    id serial PRIMARY KEY,
    name varchar(255),
    prodtype_id int REFERENCES prodtype(id),
	expired_date date,
	price float
);

INSERT INTO prodtype (name)
VALUES ('dairy products');
INSERT INTO prodtype (name)
VALUES ('meat');
INSERT INTO prodtype (name)
VALUES ('fruits');
INSERT INTO prodtype (name)
VALUES ('sauces');
INSERT INTO prodtype (name)
VALUES ('tea and coffee');
INSERT INTO prodtype (name)
VALUES ('bread');
INSERT INTO prodtype (name)
VALUES ('frozen food');

INSERT INTO product
(
	name,
	prodtype_id,
	expired_date,
	price
)
VALUES
('Майонез', 4, '2022-06-01', 64),
('Сыр', 1, '2022-04-01', 249.90),
('Сметана', 1, '2022-03-26', 61.99),
('Творог', 1, '2022-04-15', 131.50),
('Масло сливочное', 1, '2022-07-14', 139.90),

('Свинина', 2, '2022-02-14', 290.90),
('Фрикадельки', 2, '2022-04-28', 109.90),
('Колбаски', 2, '2022-04-07', 129.99),
('Фарш', 2, '2022-01-10', 189.90),

('Киви', 3, '2022-04-06', 129.90),
('Виноград', 3, '2022-04-09', 259.90),
('Бананы', 3, '2022-05-09', 89.90),
('Апельсин', 3, '2022-05-15', 109.90),
('Кокос', 3, '2022-08-23', 49.99),
('Манго', 3, '2022-06-15', 119.99),
('Авокадо', 3, '2022-05-15', 89.99),
('Клубника', 3, '2022-04-11', 329.99),
('Лайм', 3, '2022-08-01', 169.99),
('Помело', 3, '2022-05-01', 159.99),

('Кепчук', 4, '2022-08-01', 69.90),
('Сливочно-чесночный соус', 4, '2022-06-06', 49.99),
('Аджика', 4, '2022-09-01', 134.75),
('Соевый соус', 4, '2022-10-01', 119.00),

('Чай зеленый', 5, '2022-11-11', 185.90),
('Кофе', 5, '2022-12-11', 169.99),
('Чай травяной', 5, '2022-08-21', 169.50),

('Хлеб черный', 6, '2022-04-10', 45.90),
('Батон', 6, '2022-03-30', 39.00),
('Хлебцы', 6, '2022-04-27', 69.99),
('Чиабатта', 6, '2022-04-07', 57.00),

('Мороженое', 7, '2022-04-02', 99.99),
('Пельмешки', 7, '2022-04-28', 239.99),
('Клюква мороженная', 7, '2022-12-02', 199.90),
('Лобстер свежемороженый', 7, '2022-12-22', 2499.99);

SELECT p.name, t.name
FROM product AS p
JOIN prodtype AS t
ON p.prodtype_id = t.id
WHERE t.name = 'dairy products';

SELECT p.name
FROM product AS p
WHERE LOWER(p.name) LIKE '%морожен%';

SELECT p.name, p.expired_date
FROM product AS p
WHERE CURRENT_DATE > p.expired_date;

SELECT p.name, p.price
FROM product AS p
WHERE p.price = 
	(SELECT MAX(price) FROM product);

SELECT
t.name AS "Группа продуктов",
COUNT(p.name) AS "Количество в группе"
FROM product AS p
JOIN prodtype AS t
ON p.prodtype_id = t.id
GROUP BY t.name;

SELECT
p.name AS "Продукт",
t.name AS "Группа продукта"
FROM product AS p
JOIN prodtype AS t
ON p.prodtype_id = t.id
GROUP BY t.name, p.name
HAVING t.name = 'fruits' OR t.name = 'meat';

SELECT
t.name AS "Группа продукта",
COUNT(p.name) AS "Сколь осталось"
FROM product AS p
JOIN prodtype AS t
ON p.prodtype_id = t.id
GROUP BY t.name
HAVING COUNT(p.name) > 10;

SELECT
p.name AS "Продукт",
t.name AS "Группа продукта"
FROM product AS p
JOIN prodtype AS t
ON p.prodtype_id = t.id
GROUP BY p.name, t.name
ORDER BY t.name;