--liquibase formatted sql
--changeset gmercer:3
INSERT INTO person (id, name) VALUES (1, 'First User');
INSERT INTO person (id, name) VALUES (2, 'Second User');
INSERT INTO person (id, name) VALUES (3, 'Third User');