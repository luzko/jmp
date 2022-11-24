DROP TABLE IF EXISTS persons;

CREATE TABLE persons(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO persons(name, email)
VALUES ('test1', 'test1@gmail.com'),
       ('test2', 'test2@gmail.com');
