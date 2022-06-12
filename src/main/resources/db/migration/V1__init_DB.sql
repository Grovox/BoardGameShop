create sequence bucket_seq start 1 increment 1;
create sequence image_seq start 1 increment 1;
create sequence order_seq start 1 increment 1;
create sequence ordered_products_seq start 1 increment 1;
create sequence product_seq start 1 increment 1;
create sequence review_seq start 1 increment 1;
create sequence role_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;
create table buckets
(
    bucket_id int8 not null,
    user_id   int8,
    primary key (bucket_id)
);
create table buckets_products
(
    bucket_id  int8 not null,
    product_id int8 not null
);
create table images
(
    image_id           int8    not null,
    bytes              oid,
    content_type       varchar(255),
    is_preview_images  boolean not null,
    name               varchar(255),
    original_file_name varchar(255),
    size               int8,
    product_id         int8,
    primary key (image_id)
);
create table ordered_products
(
    ordered_products_id int8 not null,
    amount              int4 not null,
    order_id            int8,
    product_id          int8,
    primary key (ordered_products_id)
);
create table orders
(
    order_id     int8    not null,
    created      timestamp,
    order_price  numeric(19, 2),
    order_status varchar(255),
    paid         boolean not null,
    update       timestamp,
    user_id      int8,
    primary key (order_id)
);
create table products
(
    product_id        int8 not null,
    description       varchar(4095),
    discount          int4 not null,
    name              varchar(255),
    number_of_players varchar(255),
    preview_image_id  int8,
    price             numeric(19, 2),
    quantity_in_stock int4 not null,
    rules             varchar(4095),
    weight            varchar(255),
    primary key (product_id)
);
create table reviews
(
    review_id     int8 not null,
    created       timestamp,
    feedback_text varchar(255),
    update        timestamp,
    product_id    int8,
    user_id       int8,
    primary key (review_id)
);
create table roles
(
    role_id int8 not null,
    role    varchar(255),
    primary key (role_id)
);
create table users
(
    user_id          int8    not null,
    address          varchar(255),
    archive          boolean not null,
    city             varchar(255),
    contact_number   varchar(255),
    mail             varchar(255),
    name             varchar(255),
    password         varchar(255),
    patronymic       varchar(255),
    region           varchar(255),
    surname          varchar(255),
    bucket_bucket_id int8,
    role_id          int8,
    primary key (user_id)
);
alter table if exists buckets add constraint buckets_fk_users foreign key (user_id) references users;
alter table if exists buckets_products add constraint buckets_products_fk_products foreign key (product_id) references products;
alter table if exists buckets_products add constraint buckets_products_fk_buckets foreign key (bucket_id) references buckets;
alter table if exists images add constraint images_fk_products foreign key (product_id) references products;
alter table if exists ordered_products add constraint ordered_products_fk_orders foreign key (order_id) references orders;
alter table if exists ordered_products add constraint ordered_products_fk_products foreign key (product_id) references products;
alter table if exists orders add constraint orders_fk_users foreign key (user_id) references users;
alter table if exists reviews add constraint reviews_fk_products foreign key (product_id) references products;
alter table if exists reviews add constraint reviews_fk_users foreign key (user_id) references users;
alter table if exists users add constraint users_fk_buckets foreign key (bucket_bucket_id) references buckets;
alter table if exists users add constraint users_fk_roles foreign key (role_id) references roles;
