CREATE DATABASE starcar;

USE starcar;

CREATE TABLE brand (
	brand_id INT NOT NULL AUTO_INCREMENT,
    brand_name VARCHAR(100) NOT NULL,
    nationality VARCHAR(100) NOT NULL,
    PRIMARY KEY(brand_id)
);

CREATE TABLE car (
	car_id INT NOT NULL AUTO_INCREMENT,
    car_name VARCHAR(100) NOT NULL,
    brand_id INT NOT NULL,
    price DECIMAL(10,2),
    sold BOOLEAN NOT NULL,
    PRIMARY KEY(car_id),
    FOREIGN KEY (brand_id) REFERENCES brand (brand_id)
);

CREATE TABLE customer (
	customer_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    PRIMARY KEY(customer_id)
);

CREATE TABLE salesPerson (
	salesPerson_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    payment DECIMAL(8,2),
    PRIMARY KEY(salesPerson_id)
);

CREATE TABLE sale (
	sale_id INT NOT NULL AUTO_INCREMENT,
    car_id INT NOT NULL,
    salesPerson_id INT NOT NULL,
    customer_id  INT NOT NULL,
    PRIMARY KEY (sale_id),
	FOREIGN KEY (car_id) REFERENCES car(car_id),
    FOREIGN KEY (salesPerson_id) REFERENCES salesPerson(salesPerson_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

