USE starcar;

CREATE TABLE car (
	car_id INT NOT NULL AUTO_INCREMENT,
    car_name VARCHAR(100) NOT NULL,
    brand_id INT NOT NULL,
    price DECIMAL(10,2),
    sold BIT(1),
    PRIMARY KEY(car_id),
    FOREIGN KEY (brand_id) REFERENCES brand (brand_id)
);


