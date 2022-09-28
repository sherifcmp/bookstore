INSERT INTO users(id,USERNAME,PASSWORD) VALUES (1,'admin', '$2a$10$K3neQLfIchKm8EzVF3zn7etEoo8bTtI2vameUZqoL9rgdLd2Og35C');
INSERT INTO users(id,USERNAME,PASSWORD) VALUES (2,'bowdaar', '$2a$10$xr4cODDYLM.knCNZdwnszuTmA2yZIIcVULGhb4X4WChD9XV0K67ju');
INSERT INTO users(id,USERNAME,PASSWORD) VALUES (3,'tyvokka', '$2a$10$Xa97yAnTwU0EQTNX0WdRGuShC27nEkKIpZwK1KBq5i7GYh/qtBlp.');

INSERT INTO authors(name, user_id) VALUES ('Lowbacca','1');
INSERT INTO authors(name) VALUES ('bowdaar');
INSERT INTO authors(name) VALUES ('tyvokka');

INSERT INTO books(title, description, price, author_id) VALUES ('The Twelve Dancing Princesses', 'a German fairy tale collected by the Brothers Grimm', 37.5, 1);
INSERT INTO books(title, description, price, author_id) VALUES ('Breadcrumbs', 'a story of the struggle to hold on, and the things we leave behind', 55, 2);
INSERT INTO books(title, description, price, author_id) VALUES ('Ella Enchanted', 'a retelling of Cinderella featuring various mythical creatures including fairies, elves, ogres, gnomes, and giants', 40.25, 1);
