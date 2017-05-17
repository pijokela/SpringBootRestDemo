-- create database RestDemo;
--
-- create role pirkka;
-- alter user pirkka login;
-- GRANT ALL PRIVILEGES ON DATABASE RestDemo to pirkka;
-- 
-- CREATE USER RestDemo WITH PASSWORD 'RestDemo';
-- GRANT ALL PRIVILEGES ON DATABASE RestDemo to RestDemo;

create sequence SEQ_BOOK_ID;
alter sequence SEQ_BOOK_ID owner to restdemo;

CREATE TABLE books ( id integer, data json );
alter table books owner to restdemo;

INSERT INTO books VALUES (1,
  '{ "name": "Book the First", "author": { "first_name": "Bob", "last_name": "White" } }');
INSERT INTO books VALUES (2,
  '{ "name": "Book the Second", "author": { "first_name": "Charles", "last_name": "Xavier" } }');
INSERT INTO books VALUES (3,
  '{ "name": "Book the Third", "author": { "first_name": "Jim", "last_name": "Brown" } }');
