USE starcar;

CREATE TABLE salesPerson (
	salesPerson_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    payment DECIMAL(8,2),
    PRIMARY KEY(salesPerson_id)
) 

