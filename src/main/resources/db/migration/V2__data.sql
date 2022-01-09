INSERT INTO users (id, email, password, name, last_name)
VALUES (1, 'test', '$2a$12$rmRQHO7NohxqJWEC9CiwB./uAvNq0hPYdsde6dD2h9lLkTJ2K5tdy', 'test', 'test');

insert into roles(id, name) value (1, 'USER');
insert into roles(id, name) value (2, 'ADMIN');
insert into roles(id, name) value (3, 'SUPER_ADMIN');

INSERT INTO user_role_relations (role_id, user_id)
VALUES (1, 1);
