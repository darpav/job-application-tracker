-- insert roles
INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role_name) VALUES ('ROLE_USER');

-- insert app_users -- password: userpass
INSERT INTO app_users (username, password, role_id) VALUES ('user', '$2a$10$8hwQBNj2d.bM7.9yhGEhfuzI2HICfdckCQEAKowbQaqjnU3tHV0Pu', 2);
