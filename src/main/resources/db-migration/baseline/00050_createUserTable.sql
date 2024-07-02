--liquibase formatted sql
--changeset gmercer:1
CREATE TABLE person (id INT, name VARCHAR(255));

--changeset gmercer:2
CREATE INDEX idx_user_name ON person(name);