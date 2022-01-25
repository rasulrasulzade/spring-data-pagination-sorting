DROP SCHEMA IF EXISTS pagination_sorting_app;

CREATE SCHEMA pagination_sorting_app;

use pagination_sorting_app;

DROP TABLE IF EXISTS book;

CREATE TABLE book
(
    id     int(11) NOT NULL AUTO_INCREMENT,
    title  varchar(45) DEFAULT NULL,
    author varchar(45) DEFAULT NULL,
    PRIMARY KEY (id)
);