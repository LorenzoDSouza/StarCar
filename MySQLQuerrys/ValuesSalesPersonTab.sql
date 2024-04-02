USE starcar;

INSERT INTO salesPerson (first_name, last_name, payment) VALUES
    ('John', 'Doe', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('Jane', 'Smith', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('Michael', 'Johnson', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('Emily', 'Brown', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('William', 'Taylor', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('Emma', 'Anderson', ROUND(RAND() * (2000 - 750) + 750, 2)),
    ('James', 'Wilson', ROUND(RAND() * (2000 - 750) + 750, 2));