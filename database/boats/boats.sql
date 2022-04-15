CREATE TABLE yachttype
(
id serial PRIMARY KEY,
name varchar(255)
);

CREATE TABLE yachts
(
id serial PRIMARY KEY,
name varchar(255),
lengths numeric,
engine boolean,
yachttype_id int references yachttype(id)
);

INSERT INTO yachttype (name) VALUES ('парусная');
INSERT INTO yachttype (name) VALUES ('моторная');
INSERT INTO yachttype (name) VALUES ('круизная');
INSERT INTO yachttype (name) VALUES ('спортивная');
INSERT INTO yachttype (name) VALUES ('прогулочная');
INSERT INTO yachttype (name) VALUES ('суперяхта');
INSERT INTO yachttype (name) VALUES ('мегаяхта');

INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Oceanis 34.1', 11, false, 1);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Oceanis 34.1', 11, false, 3);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('First 27', 8, false, 1);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('First 27', 8, false, 4);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Yacht 62', 20, false, 1);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Velvette 23', 7, true, 2);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Velvette 23', 7, true, 5);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Velvette 29 Envy', 9, true, 2);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Velvette 29 Envy', 9, true, 3);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Velvette 29 Envy', 9, true, 4);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Galeon 780 Crystal', 25, true, 2);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Galeon 780 Crystal', 25, true, 6);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Wally Ace 42', 42, true, 2);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Wally Ace 42', 42, true, 7);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Damen SeaXplorer 62', 65, true, 7);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Scheherazade', 140, true, 7);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('BLACK PEARL', 106, false, 7);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('BLACK PEARL', 106, false, 1);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Sailing Yacht A', 142, false, 1);
INSERT INTO yachts (name, lengths, engine, yachttype_id)
VALUES ('Sailing Yacht A', 142, false, 7);

SELECT
	boat.name,
	btype.name
FROM yachts as boat
INNER JOIN yachttype as btype ON
	boat.yachttype_id = btype.id;

SELECT
	boat.name,
	btype.name,
	boat.lengths
FROM yachts as boat
INNER JOIN yachttype as btype ON
	boat.yachttype_id = btype.id
WHERE boat.lengths > 12;

SELECT
	boat.name,
	btype.name,
	boat.lengths,
	boat.engine
FROM yachts as boat
INNER JOIN yachttype as btype ON
	boat.yachttype_id = btype.id
WHERE boat.yachttype_id = 7;