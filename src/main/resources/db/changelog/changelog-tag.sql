-- liquibase formatted sql

-- changeset Buru Csongor:1

CREATE TABLE tag
(
    id   bigint NOT NULL AUTO_INCREMENT,
    name varchar(64) DEFAULT NULL,
    PRIMARY KEY (id)
);