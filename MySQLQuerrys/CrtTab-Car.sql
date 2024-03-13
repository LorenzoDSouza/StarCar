USE starcar;

/*CREATE TABLE car (
	car_id INT NOT NULL AUTO_INCREMENT,
    car_name VARCHAR(100) NOT NULL,
    brand_id INT NOT NULL,
    PRIMARY KEY(car_id),
    FOREIGN KEY (brand_id) REFERENCES brand (brand_id)
)*/

	ALTER TABLE car
    ADD COLUMN price DECIMAL(10,2);