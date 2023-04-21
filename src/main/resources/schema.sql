DROP TABLE IF EXISTS product;
CREATE TABLE product(
id INT IDENTITY PRIMARY KEY,
name VARCHAR(200),
description VARCHAR(200),
price DOUBLE,
quantity INT
);
INSERT INTO  product (id, name, description, price, quantity) VALUES (1, 'Book', 'It - Stephen King', 10.05, 1);
INSERT INTO  product (id, name, description, price, quantity) VALUES (2, 'CD', 'Rubber Soul - The Beatles', 19.99, 2);
INSERT INTO  product (id, name, description, price, quantity) VALUES (3, 'Pencil', 'Blue Ink Pencil', 0.95, 20);
