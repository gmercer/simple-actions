--liquibase formatted sql
--changeset gmercer:1
CREATE TABLE user (user_id INT, name VARCHAR(255));

--changeset gmercer:2
CREATE INDEX idx_user_name ON user(name);