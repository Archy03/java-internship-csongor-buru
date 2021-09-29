-- liquibase formatted sql

-- changeset Buru Csongor:1

CREATE TABLE comment
(
    id      bigint NOT NULL AUTO_INCREMENT,
    text    longtext DEFAULT NULL,
    user_id bigint NOT NULL,
    post_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_comment_post_id FOREIGN KEY (post_id) REFERENCES post (id),
    CONSTRAINT fk_comment_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);