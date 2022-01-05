insert into users
    (email, password, name, last_name)
values ('testEmail', 'testpswrd', 'testname', 'test_lastname');

update users
set email   = 'upd_Email',
    password= 'upd_Password'
where email = 'testEmail'
  and password = 'testpswrd';

delete
from users
where email = ''
  and password = '';

insert into roles(name) value ('USER');
insert into roles(name) value ('ADMIN');
insert into roles(name) value ('SUPER_ADMIN');

select *
from roles;

insert into products (title, price, description)
values ('book', '99.9', '"java for idiots(like meeee)"');
update products
SET title       = 'Book',
    price       = '99.5',
    description = '"Java for beginners"'
where id = 1;

select *
from products;