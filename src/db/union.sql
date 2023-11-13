CREATE TABLE movie (
    id serial PRIMARY KEY,
    movie_name text,
    director text
);

CREATE TABLE book (
    id serial PRIMARY KEY,
    title text,
    author text
);

INSERT INTO movie (movie_name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

       SELECT title
       FROM book
       EXCEPT
       SELECT movie_name
       FROM movie;

        SELECT movie_name
       FROM movie
       INTERSECT
       SELECT title
       FROM book;

SELECT movie_name FROM movie
UNION
 SELECT title FROM book
EXCEPT
SELECT title FROM book
INTERSECT
SELECT movie_name FROM movie;



