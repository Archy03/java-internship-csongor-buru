-- liquibase formatted sql

-- changeset Buru Csongor:1

CREATE TABLE post
(
    id         bigint    NOT NULL AUTO_INCREMENT,
    title      varchar(64) DEFAULT NULL,
    content    text,
    user_id    bigint    NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);