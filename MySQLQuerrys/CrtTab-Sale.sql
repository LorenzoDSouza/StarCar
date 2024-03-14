USE starcar;

CREATE TABLE sale (
	sale_id INT NOT NULL AUTO_INCREMENT,
    car_id INT NOT NULL,
    salesPerson_id INT NOT NULL,
    customer_id  INT NOT NULL,
    PRIMARY KEY (sale_id),
	FOREIGN KEY (car_id) REFERENCES car(car_id),
    FOREIGN KEY (salesPerson_id) REFERENCES salesPerson(salesPerson_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
)