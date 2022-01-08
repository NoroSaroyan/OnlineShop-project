create table users
(
    id        int auto_increment primary key,
    email     varchar(50)  not null unique,
    password  varchar(250) not null,
    name      varchar(100) not null,
    last_name varchar(100) not null

);

create table roles
(
    id   int auto_increment primary key,
    name varchar(20) not null
);

create table user_role_relations
(
    -- TODO fix table
    role_id int not null,
    user_id int not null,
    -- TODO check
    foreign key (role_id) references roles (id),
    foreign key (user_id) references users (id)
);

create table orders
(
    id               int primary key auto_increment,
    product_id       int    not null,
    user_id          int    not null,
    price            double not null,
    status           varchar(20),
    product_quantity int    not null
);

create table categories
(
    id          int primary key auto_increment,
    name        varchar(100) not null unique,
    description varchar(250) not null
);
create table products
(
    id          int auto_increment primary key,
    title       varchar(100) not null,
    price       double       not null,
    description varchar(250) not null
);
create table category_product_relations
(
    category_id int not null,
    product_id  int not null,

    -- TODO check
    foreign key (category_id) references categories (id),
    foreign key (product_id) references products (id)
);

create table carts
(
    id int auto_increment primary key,
    user_id int not null,

    foreign key (user_id) references users (id)
);

create table cart_product_relations
(
    cart_id int not null,
    product_id  int not null,

    -- TODO check
    foreign key (cart_id) references carts (id),
    foreign key (product_id) references products (id)
);

