create table users (
    id SERIAL4 PRIMARY KEY NOT NULL,
    username varchar(32) UNIQUE NOT NULL,
    name varchar(32) UNIQUE NOT NULL,
    surname varchar(48) UNIQUE NOT NULL,
    password_hash varchar(80) NOT NULL

);

create table Product (
    id SERIAL4 PRIMARY KEY NOT NULL,
    pubId uuid UNIQUE NOT NULL,
    name varchar(56) NOT NULL
    
)