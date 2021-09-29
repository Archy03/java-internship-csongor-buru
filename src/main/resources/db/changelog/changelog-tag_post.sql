-- liquibase formatted sql

-- changeset Buru Csongor:1

CREATE TABLE tag_post
(
    post_id bigint NOT NULL,
    tag_id  bigint NOT NULL,
    PRIMARY KEY (post_id, tag_id),
    CONSTRAINT fk_tag_post_post FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tag_post_tag FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE ON UPDATE CASCADE
);