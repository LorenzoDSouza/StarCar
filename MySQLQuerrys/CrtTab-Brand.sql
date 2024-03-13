USE starcar;

CREATE TABLE brand (
	brand_id INT NOT NULL AUTO_INCREMENT,
    brand_name VARCHAR(100) NOT NULL,
    nationality VARCHAR(100) NOT NULL,
    PRIMARY KEY(brand_id)
)