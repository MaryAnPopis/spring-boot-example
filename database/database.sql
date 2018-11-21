CREATE DATABASE magnaalianza
USE magnaalianza

CREATE TABLE tproducer(
  id int primary key AUTO_INCREMENT NOT NULL,
  firstname varchar(25),
  nominal_address varchar(100),
  province varchar(50),
  canton varchar(50),
  district varchar(50),
  identification varchar(20),
  company_name varchar(100)
)

CREATE TABLE tfarmland(
  id int primary key AUTO_INCREMENT NOT NULL,
  name varchar(100),
  id_producer int NOT NULL,
  FOREIGN KEY (id_producer) REFERENCES tproducer (id)
)

CREATE TABLE tcoffee(
  id int primary key AUTO_INCREMENT NOT NULL,
  name varchar(25)
)

CREATE TABLE tfarmland_x_coffee(
    id int primary key AUTO_INCREMENT NOT NULL,
    id_farmland int,
    id_coffee int,
    FOREIGN KEY (id_farmland) REFERENCES tfarmland (id),
    FOREIGN KEY (id_coffee) REFERENCES tcoffee (id)
)
