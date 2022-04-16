CREATE TABLE users
(
    id            SERIAL8 PRIMARY KEY NOT NULL,
    username      varchar(32) UNIQUE  NOT NULL,
    password_hash varchar(80)         NOT NULL,
    name          varchar(32)         NOT NULL,
    surname       varchar(48)         NOT NULL
);

CREATE TABLE user_roles
(
    id SERIAL8 PRIMARY KEY NOT NULL ,
    user_id int8 NOT NULL ,
    role int2 NOT NULL ,
    FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE company_info
(
    id serial PRIMARY KEY NOT NULL ,
    name varchar(256) NOT NULL ,
    country_code char(2)             NOT NULL,
    city         varchar(256)        NOT NULL,
    nip          varchar(128)        NOT NULL,
    phone_number varchar(28),
    email        varchar(128)
);

CREATE TABLE business_entities
(
    id           SERIAL8 PRIMARY KEY NOT NULL,
    name         varchar(256)        NOT NULL,
    country_code char(2)             NOT NULL,
    city         varchar(256)        NOT NULL,
    nip          varchar(128)        NOT NULL,
    phone_number varchar(28),
    email        varchar(128)
);

CREATE TABLE items
(
    id                SERIAL8 PRIMARY KEY NOT NULL,
    code_name         varchar(32)         NOT NULL,
    name              varchar(192)        NOT NULL,
    type              int2                NOT NULL, -- enum
    price             decimal             NOT NULL,
    tax_rate          DECIMAL             NOT NULL,
    description       text                NOT NULL,
    quantity_in_stock int8                NOT NULL

);



