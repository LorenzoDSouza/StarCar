USE starcar;

INSERT INTO car (car_name, brand_id, price, sold)
	VALUES
    ('Civic', 1, 25000.00, RAND() > 0.5),
    ('Accord', 1, 30000.00, RAND() > 0.5),
    ('CR-V', 1, 28000.00, RAND() > 0.5),
    ('Pilot', 1, 35000.00, RAND() > 0.5),
    ('Odyssey', 1, 32000.00, RAND() > 0.5),

    ('Camry', 2, 27000.00, RAND() > 0.5),
    ('Corolla', 2, 22000.00, RAND() > 0.5),
    ('RAV4', 2, 29000.00, RAND() > 0.5),
    ('Highlander', 2, 35000.00, RAND() > 0.5),
    ('Prius', 2, 25000.00, RAND() > 0.5),

    ('Altima', 3, 26000.00, RAND() > 0.5),
    ('Sentra', 3, 22000.00, RAND() > 0.5),
    ('Rogue', 3, 28000.00, RAND() > 0.5),
    ('Maxima', 3, 34000.00, RAND() > 0.5),
    ('Pathfinder', 3, 33000.00, RAND() > 0.5),

    ('Mustang', 4, 32000.00, RAND() > 0.5),
    ('F-150', 4, 40000.00, RAND() > 0.5),
    ('Focus', 4, 22000.00, RAND() > 0.5),
    ('Escape', 4, 28000.00, RAND() > 0.5),
    ('Explorer', 4, 35000.00, RAND() > 0.5),

    ('Silverado', 5, 38000.00, RAND() > 0.5),
    ('Malibu', 5, 27000.00, RAND() > 0.5),
    ('Equinox', 5, 30000.00, RAND() > 0.5),
    ('Traverse', 5, 34000.00, RAND() > 0.5),
    ('Tahoe', 5, 50000.00, RAND() > 0.5),

    ('Model S', 6, 80000.00, RAND() > 0.5),
    ('Model 3', 6, 50000.00, RAND() > 0.5),
    ('Model X', 6, 90000.00, RAND() > 0.5),
    ('Model Y', 6, 55000.00, RAND() > 0.5),
    ('Cybertruck', 6, 70000.00, RAND() > 0.5),

    ('Wrangler', 7, 35000.00, RAND() > 0.5),
    ('Cherokee', 7, 30000.00, RAND() > 0.5),
    ('Grand Cherokee', 7, 40000.00, RAND() > 0.5),
    ('Renegade', 7, 25000.00, RAND() > 0.5),
    ('Compass', 7, 27000.00, RAND() > 0.5),

    ('3 Series', 8, 45000.00, RAND() > 0.5),
    ('5 Series', 8, 55000.00, RAND() > 0.5),
    ('X3', 8, 47000.00, RAND() > 0.5),
    ('X5', 8, 65000.00, RAND() > 0.5),
    ('7 Series', 8, 80000.00, RAND() > 0.5),

    ('911', 9, 90000.00, RAND() > 0.5),
    ('Cayenne', 9, 80000.00, RAND() > 0.5),
    ('Macan', 9, 60000.00, RAND() > 0.5),
    ('Panamera', 9, 95000.00, RAND() > 0.5),
    ('Taycan', 9, 100000.00, RAND() > 0.5),

    ('A4', 10, 40000.00, RAND() > 0.5),
    ('A6', 10, 50000.00, RAND() > 0.5),
    ('Q5', 10, 45000.00, RAND() > 0.5),
    ('Q7', 10, 60000.00, RAND() > 0.5),
    ('TT', 10, 55000.00, RAND() > 0.5),

    ('C-Class', 11, 45000.00, RAND() > 0.5),
    ('E-Class', 11, 55000.00, RAND() > 0.5),
    ('GLE', 11, 60000.00, RAND() > 0.5),
    ('S-Class', 11, 90000.00, RAND() > 0.5),
    ('GLC', 11, 48000.00, RAND() > 0.5),

    ('488 GTB', 12, 300000.00, RAND() > 0.5),
    ('F8 Tributo', 12, 350000.00, RAND() > 0.5),
    ('Portofino', 12, 250000.00, RAND() > 0.5),
    ('Roma', 12, 350000.00, RAND() > 0.5),
    ('SF90 Stradale', 12, 600000.00, RAND() > 0.5),

    ('Aventador', 13, 400000.00, RAND() > 0.5),
    ('Huracan', 13, 250000.00, RAND() > 0.5),
    ('Urus', 13, 250000.00, RAND() > 0.5),
    ('Gallardo', 13, 300000.00, RAND() > 0.5),
    ('Diablo', 13, 500000.00, RAND() > 0.5);