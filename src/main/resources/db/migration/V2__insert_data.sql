INSERT INTO country (name, continent) VALUES ('UK', 'Europe'), ('USA', 'North America');

INSERT INTO author (created_at, updated_at, name, surname, country_id)
VALUES (NOW(), NOW(), 'J.K.', 'Rowling', 1), (NOW(), NOW(), 'George R.R.', 'Martin', 2);

INSERT INTO book (created_at, updated_at, name, category, state, available_copies, author_id)
VALUES
    (NOW(), NOW(), 'Harry Potter', 'FANTASY', 'GOOD', 5, 1),
    (NOW(), NOW(), 'A Game of Thrones', 'FANTASY', 'GOOD', 3, 2),
    (NOW(), NOW(), 'Old Damaged Book', 'DRAMA', 'BAD', 0, 1);