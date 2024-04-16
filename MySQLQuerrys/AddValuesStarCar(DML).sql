USE starcar;

INSERT INTO brand(brand_name, nationality)
VALUES 
    ('Honda', 'Japan'),
    ('Toyota', 'Japan'),
    ('Nissan', 'Japan'),
    ('Ford', 'USA'),
    ('Chevrolet', 'USA'),
    ('Tesla', 'USA'),
    ('Jeep', 'USA'),
    ('BMW', 'Germany'),
    ('Porsche', 'Germany'),
    ('Audi', 'Germany'),
    ('Mercedez_Benz', 'Germany'),
    ('Ferrari', 'Italy'),
    ('Lamborghini', 'Italy');
    
INSERT INTO car (car_name, brand_id, price, sold)
VALUES
    ('Civic', 1, 25000.00, 1),
    ('Accord', 1, 30000.00, 0),
    ('CR-V', 1, 28000.00, 1),
    ('Pilot', 1, 35000.00, 1),
    ('Odyssey', 1, 32000.00, 0),

    ('Camry', 2, 27000.00, 1),
    ('Corolla', 2, 22000.00, 0),
    ('RAV4', 2, 29000.00, 0),
    ('Highlander', 2, 35000.00, 1),
    ('Prius', 2, 25000.00, 0),

    ('Altima', 3, 26000.00, 0),
    ('Sentra', 3, 22000.00, 1),
    ('Rogue', 3, 28000.00, 1),
    ('Maxima', 3, 34000.00, 0),
    ('Pathfinder', 3, 33000.00, 0),

    ('Mustang', 4, 32000.00, 0),
    ('F-150', 4, 40000.00, 0),
    ('Focus', 4, 22000.00, 0),
    ('Escape', 4, 28000.00, 0),
    ('Explorer', 4, 35000.00, 1),

    ('Silverado', 5, 38000.00, 1),
    ('Malibu', 5, 27000.00, 1),
    ('Equinox', 5, 30000.00, 0),
    ('Traverse', 5, 34000.00, 1),
    ('Tahoe', 5, 50000.00, 1),

    ('Model S', 6, 80000.00, 1),
    ('Model 3', 6, 50000.00, 0),
    ('Model X', 6, 90000.00, 1),
    ('Model Y', 6, 55000.00, 0),
    ('Cybertruck', 6, 70000.00, 1),

    ('Wrangler', 7, 35000.00, 0),
    ('Cherokee', 7, 30000.00, 1),
    ('Grand Cherokee', 7, 40000.00, 0),
    ('Renegade', 7, 25000.00, 0),
    ('Compass', 7, 27000.00, 1),

    ('3 Series', 8, 45000.00, 0),
    ('5 Series', 8, 55000.00, 1),
    ('X3', 8, 47000.00, 1),
    ('X5', 8, 65000.00, 1),
    ('7 Series', 8, 80000.00, 0),

    ('911', 9, 90000.00, 0),
    ('Cayenne', 9, 80000.00, 0),
    ('Macan', 9, 60000.00, 1),
    ('Panamera', 9, 95000.00, 0),
    ('Taycan', 9, 100000.00, 1),

    ('A4', 10, 40000.00, 1),
    ('A6', 10, 50000.00, 1),
    ('Q5', 10, 45000.00, 0),
    ('Q7', 10, 60000.00, 1),
    ('TT', 10, 55000.00, 0),

    ('C-Class', 11, 45000.00, 0),
    ('E-Class', 11, 55000.00, 0),
    ('GLE', 11, 60000.00, 1),
    ('S-Class', 11, 90000.00, 0),
    ('GLC', 11, 48000.00, 1),

    ('488 GTB', 12, 300000.00, 0),
    ('F8 Tributo', 12, 350000.00, 0),
    ('Portofino', 12, 250000.00, 1),
    ('Roma', 12, 350000.00, 0),
    ('SF90 Stradale', 12, 600000.00, 0),

    ('Aventador', 13, 400000.00, 0),
    ('Huracan', 13, 250000.00, 0),
    ('Urus', 13, 250000.00, 0),
    ('Gallardo', 13, 300000.00, 0),
    ('Diablo', 13, 500000.00, 0);

INSERT INTO customer (first_name, last_name)
	VALUES
    ('David', 'Johnson'),
    ('Sarah', 'Williams'),
    ('Christopher', 'Brown'),
    ('Jennifer', 'Davis'),
    ('Matthew', 'Wilson'),
    ('Jessica', 'Taylor'),
    ('Andrew', 'Anderson'),
    ('Lucas', 'Thomas'),
    ('Sophia', 'Jackson'),
    ('Alexander', 'White'),
    ('Isabella', 'Harris'),
    ('Mason', 'Martin'),
    ('Mia', 'Thompson'),
    ('Ethan', 'Garcia'),
    ('Charlotte', 'Martinez'),
    ('Liam', 'Robinson'),
    ('Amelia', 'Clark'),
    ('Jacob', 'Lee'),
    ('Harper', 'Walker'),
    ('Daniel', 'Hall'),
    ('Evelyn', 'Young'),
    ('Logan', 'Allen'),
    ('Abigail', 'King'),
    ('Jackson', 'Wright');

INSERT INTO salesPerson (first_name, last_name, payment) VALUES
    ('John', 'Doe', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('Jane', 'Smith', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('Michael', 'Johnson', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('Emily', 'Brown', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('William', 'Taylor', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('Emma', 'Anderson', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('James', 'Wilson', ROUND(RAND() * (2000 - 750) + 750, 2));
    
INSERT INTO sale(car_id, salesPerson_id, customer_id)
VALUES
		(1, 3, 1),
        (3, 5, 2),
        (4, 1, 3),
        (6, 3, 4),
        (9, 2, 6),
        (12, 6, 7),
        (13, 6, 8),
        (20, 1, 9),
        (21, 2, 10),
        (22, 3, 11),
        (24, 2, 12),
        (25, 5, 13),
        (26, 4, 14),
        (28, 7, 15),
        (30, 4, 16),
        (32, 3, 17),
        (35, 1, 5),
        (37, 6, 18),
		(38, 2, 11),
		(39, 1, 19),
		(43, 7, 20),
		(58, 6, 21),
        (45, 5, 2),
        (46, 3, 22),
        (47, 3, 18),
        (49, 2, 23),
        (53, 6, 5),
        (55, 1, 24);
       