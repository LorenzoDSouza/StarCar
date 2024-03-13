USE starcar;

CREATE TABLE customer (
	customer_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    PRIMARY KEY(customer_id)
)