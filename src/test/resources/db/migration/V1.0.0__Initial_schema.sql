create table author
(
    id          int8 not null,
    created_at  timestamp(9),
    modified_at timestamp(9),
    bio         varchar(255),
    birth_date  date,
    name        varchar(255),
    primary key (id)
);
create table cart
(
    id          int8 not null,
    created_at  timestamp(9),
    modified_at timestamp(9),
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
    created_at  timestamp(9),
    modified_at timestamp(9),
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
    created_at  timestamp(9),
    modified_at timestamp(9),
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
create table product
(
    id                int8 not null,
    created_at        timestamp(9),
    modified_at       timestamp(9),
    description       varchar(512),
    image             varchar(255),
    name              varchar(255),
    preview_file_name varchar(255),
    price             int8,
    stock             int8,
    author_id         int8,
    genre_id          int8,
    primary key (id)
);
create sequence hibernate_sequence start with 1000 increment by 1;
alter table if exists cart_products add constraint FK3ajor63f7qykde5i7x0nmdkj0 foreign key (fk_product) references product;
alter table if exists cart_products add constraint FKbrui6s13kpx85jexpigese280 foreign key (fk_cart) references cart;
alter table if exists eshop_order_products add constraint FKadkq97m9xjv24k4554sorsuin foreign key (fk_product) references product;
alter table if exists eshop_order_products add constraint FK6npdvxhdrkj4tn7sooy9xegu6 foreign key (fk_eshop_order) references eshop_order;
alter table if exists product add constraint FKayq8bdn719whg00c0wor7skjp foreign key (author_id) references author;
alter table if exists product add constraint FKlgp6x71g08sy3a84mur67uuht foreign key (genre_id) references genre;