INSERT INTO users (id, email, password, name, last_name)
VALUES (1, 'test@gmail.com', '$2a$12$rmRQHO7NohxqJWEC9CiwB./uAvNq0hPYdsde6dD2h9lLkTJ2K5tdy', 'test', 'test');

INSERT INTO users (id, email, password, name, last_name)
VALUES (2, 'admin@gmail.com', '$2a$12$rmRQHO7NohxqJWEC9CiwB./uAvNq0hPYdsde6dD2h9lLkTJ2K5tdy', 'admin', 'admin');

INSERT INTO users (id, email, password, name, last_name)
VALUES (3, 'sadmin@gmail.com', '$2a$12$rmRQHO7NohxqJWEC9CiwB./uAvNq0hPYdsde6dD2h9lLkTJ2K5tdy', 'sadmin', 'sadmin');

insert into roles(id, name) value (1, 'USER');
insert into roles(id, name) value (2, 'ADMIN');
insert into roles(id, name) value (3, 'SUPER_ADMIN');


INSERT INTO user_role_relations (role_id, user_id)
        VALUES (1, 1);
INSERT INTO user_role_relations (role_id, user_id)
        VALUES (2, 2);
INSERT INTO user_role_relations (role_id, user_id)
        VALUES (3, 3);

INSERT INTO products (id, title, price, description) VALUES (3, 'book', 25.4, 'book');
INSERT INTO products (id, title, price, description) VALUES (4, 'car', 4500.5, 'car');
INSERT INTO products (id, title, price, description) VALUES (5, 'iphone', 1220.15, 'phone from apple');
INSERT INTO products (id, title, price, description) VALUES (6, 'Pants', 25.5, 'good pants');
INSERT INTO products (id, title, price, description) VALUES (7, 'shoes', 40, 'shoes');