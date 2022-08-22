create sequence hibernate_sequence start 1000 increment 1;
create table author
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    bio         varchar(255),
    birth_date  date,
    name        varchar(255),
    primary key (id)
);
create table cart
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    primary key (id)
);
create table cart_products
(
    fk_cart    int8 not null,
    fk_product int8 not null,
    primary key (fk_cart, fk_product)
);
create table eshop_order
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    status      varchar(255),
    primary key (id)
);
create table eshop_order_products
(
    fk_eshop_order int8 not null,
    fk_product     int8 not null,
    primary key (fk_eshop_order, fk_product)
);
create table genre
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
create table product
(
    id          int8 not null,
    created_at  timestamp,
    modified_at timestamp,
    description varchar(512),
    image       varchar(255),
    name        varchar(255),
    price       int8,
    stock       int8,
    author_id   int8,
    genre_id    int8,
    primary key (id)
);
alter table if exists cart_products
    add constraint fk_product_id foreign key (fk_product) references product;
alter table if exists cart_products
    add constraint fk_cart_id foreign key (fk_cart) references cart;
alter table if exists eshop_order_products
    add constraint fk_eshop_order_product_id foreign key (fk_product) references product;
alter table if exists eshop_order_products
    add constraint fk_eshop_order_id foreign key (fk_eshop_order) references eshop_order;
alter table if exists product
    add constraint fk_product_author_id foreign key (author_id) references author;
alter table if exists product
    add constraint fk_product_genre_id foreign key (genre_id) references genre;