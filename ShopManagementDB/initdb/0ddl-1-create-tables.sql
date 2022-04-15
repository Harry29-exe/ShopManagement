CREATE TABLE users
(
    id            SERIAL8 PRIMARY KEY NOT NULL,
    username      varchar(32) UNIQUE  NOT NULL,
    password_hash varchar(80)         NOT NULL,
    name          varchar(32)         NOT NULL,
    surname       varchar(48)         NOT NULL
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
    code_name         varchar(64)         NOT NULL,
    name              varchar(192)        NOT NULL,
    type              int2                NOT NULL, -- enum
    price             decimal             NOT NULL,
    tax_rate          DECIMAL             NOT NULL,
    description       text                NOT NULL,
    quantity_in_stock int8                NOT NULL

);

CREATE TABLE purchase_invoices
(
    id            SERIAL8 PRIMARY KEY NOT NULL,
    correction_id int8 UNIQUE,
    entity        int8                NOT NULL,
    orderedBy     int8                NOT NULL,
    FOREIGN KEY (correction_id)
        REFERENCES purchase_invoices (id),
    FOREIGN KEY (entity)
        REFERENCES business_entities (id),
    FOREIGN KEY (orderedBy)
        REFERENCES users (id)
);

CREATE TABLE purchase_invoice_items
(
    id                  SERIAL8 PRIMARY KEY NOT NULL,
    purchase_invoice_id int8                NOT NULL,
    item_id             int8                NOT NULL,
    quantity            int8                NOT NULL,
    price               DECIMAL             NOT NULL,
    tax_rate            DECIMAL             NOT NULL,
    discount            DECIMAL             NOT NULL,

    FOREIGN KEY (purchase_invoice_id)
        REFERENCES purchase_invoices (id),
    FOREIGN KEY (item_id)
        REFERENCES items (id)
);

CREATE TABLE sales_invoices
(
    id            SERIAL8 PRIMARY KEY NOT NULL,
    correction_id int8 UNIQUE,
    seller        int8                NOT NULL,
    entity        int8                NOT NULL,
    FOREIGN KEY (correction_id)
        REFERENCES sales_invoices (id),
    FOREIGN KEY (seller)
        REFERENCES users (id),
    FOREIGN KEY (entity)
        REFERENCES business_entities (id)
);

CREATE TABLE sales_invoice_items
(
    id               SERIAL8 PRIMARY KEY NOT NULL,
    sales_invoice_id int8                NOT NULL,
    item_id          int8                NOT NULL,
    quantity         int8                NOT NULL,
    price            DECIMAL             NOT NULL,
    tax_rate         DECIMAL             NOT NULL,
    discount         DECIMAL             NOT NULL,

    FOREIGN KEY (sales_invoice_id)
        REFERENCES sales_invoices (id),
    FOREIGN KEY (item_id)
        REFERENCES items (id)
);

CREATE TABLE receipts
(
    id SERIAL8 PRIMARY KEY NOT NULL ,
    seller int8 NOT NULL ,
    FOREIGN KEY (seller)
        REFERENCES users(id)
);

CREATE TABLE receipt_items
(
    id               SERIAL8 PRIMARY KEY NOT NULL,
    receipt_id int8                NOT NULL,
    item_id          int8                NOT NULL,
    quantity         int8                NOT NULL,
    price            DECIMAL             NOT NULL,
    tax_rate         DECIMAL             NOT NULL,
    discount         DECIMAL             NOT NULL,
    FOREIGN KEY (id)
        REFERENCES sales_invoices (id),
    FOREIGN KEY (item_id)
        REFERENCES items (id)
)




