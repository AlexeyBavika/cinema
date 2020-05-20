DROP DATABASE IF EXISTS cinema;

CREATE DATABASE cinema;

USE cinema;

CREATE TABLE movies
(
    id          BIGINT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title       VARCHAR(256)           NOT NULL,
    description VARCHAR(1024)          NOT NULL
);