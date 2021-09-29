-- liquibase formatted sql

-- changeset Buru Csongor:1

CREATE TABLE user
(
    id         bigint      NOT NULL AUTO_INCREMENT,
    first_name varchar(64) DEFAULT NULL,
    last_name  varchar(64) DEFAULT NULL,
    email      varchar(64) DEFAULT NULL,
    password   varchar(32) NOT NULL,
    created_at timestamp   NOT NULL,
    updated_at timestamp   NOT NULL,
    PRIMARY KEY (id)
);