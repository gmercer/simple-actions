--liquibase formatted sql
--changeset gmercer:3
INSERT INTO user (id, name) VALUES (1, 'First User');
INSERT INTO user (id, name) VALUES (2, 'Second User');
INSERT INTO user (id, name) VALUES (3, 'Third User');