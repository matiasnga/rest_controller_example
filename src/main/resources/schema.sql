
CREATE TABLE employee(
id INT IDENTITY PRIMARY KEY, 
name VARCHAR(200),
role VARCHAR(200)
);
INSERT INTO  employee (id, name, role) VALUES (1, 'Matias Gallo', 'admin');
