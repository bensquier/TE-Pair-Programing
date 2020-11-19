/*Script to build Animal Hospital Database*/

/*Create Tables*/
BEGIN TRANSACTION;

CREATE TABLE pet (
pet_id serial,
name varchar(25) NOT NULL,
type varchar(25) NOT NULL,
age int NULL,
customer_id int NOT NULL,
procedure_id int NOT NULL,

CONSTRAINT pk_pet PRIMARY KEY(pet_id) 
);

CREATE TABLE customer (
customer_id serial,
fname varchar(25) NULL,
lname varchar(25) NOT NULL,
pet_id int NOT NULL,
address_id int NOT NULL,

CONSTRAINT pk_customer PRIMARY KEY(customer_id)
);

CREATE TABLE address (
address_id serial,
address varchar(50) NOT NULL,
address_two varchar(50) NULL,
city varchar(25) NOT NULL,
district varchar(25) NOT NULL,
country varchar(25) NOT NULL,
zip_code varchar(10) NOT NULL,

CONSTRAINT pk_address PRIMARY KEY(address_id)
);

CREATE TABLE procedure (
procedure_id serial,
description varchar(100) NOT NULL,
date varchar(11) NOT NULL,
pet_id int NOT NULL,
price money NOT NULL,

CONSTRAINT pk_procedure PRIMARY KEY(procedure_id)
);

CREATE TABLE pet_customer (
pet_id int NOT NULL,
customer_id int NOT NULL,

CONSTRAINT pk_pet_customer PRIMARY KEY(pet_id, customer_id)
);

CREATE TABLE customer_address (
customer_id int NOT NULL,
address_id int NOT NULL,

CONSTRAINT pk_customer_address PRIMARY KEY(customer_id, address_id)
);

CREATE TABLE pet_procedure (
pet_id int NOT NULL,
procedure_id int NOT NULL,

CONSTRAINT pk_pet_procedure PRIMARY KEY(pet_id, procedure_id)
);

CREATE TABLE invoice (
invoice_id serial,
hospital_name varchar(100) NOT NULL,
date varchar(11) NOT NULL,
customer_id int NOT NULL,
pet_id int NOT NULL,
procedure_id int NOT NULL,
price money NOT NULL,
tax money NOT NULL,
total_price money NOT NULL,

CONSTRAINT pk_invoice PRIMARY KEY(invoice_id)
);

/*Alter Statements*/

BEGIN TRANSACTION;

ALTER TABLE pet
ADD CONSTRAINT fk_pet_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
ADD CONSTRAINT fk_pet_procedure FOREIGN KEY (procedure_id) REFERENCES procedure(procedure_id);

ALTER TABLE customer
ADD CONSTRAINT fk_customer_pet FOREIGN KEY (pet_id) REFERENCES pet(pet_id),
ADD CONSTRAINT fk_customer_address FOREIGN KEY (address_id) REFERENCES address(address_id);

ALTER TABLE procedure 
ADD CONSTRAINT fk_procedure_pet FOREIGN KEY (pet_id) REFERENCES pet(pet_id);

ALTER TABLE invoice
ADD CONSTRAINT fk_invoice_pet FOREIGN KEY (pet_id) REFERENCES pet(pet_id),
ADD CONSTRAINT fk_invoice_procedure FOREIGN KEY (procedure_id) REFERENCES procedure(procedure_id),
ADD CONSTRAINT fk_invoice_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id);

ALTER TABLE pet_customer
ADD CONSTRAINT fk_pet_customer FOREIGN KEY (pet_id) REFERENCES pet(pet_id),
ADD CONSTRAINT fk_customer_pet FOREIGN KEY (customer_id) REFERENCES customer(customer_id);

ALTER TABLE customer_address
ADD CONSTRAINT fk_customer_address FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
ADD CONSTRAINT fk_address_customer FOREIGN KEY (address_id) REFERENCES address(address_id);

ALTER TABLE pet_procedure
ADD CONSTRAINT fk_pet_procedure FOREIGN KEY (pet_id) REFERENCES pet(pet_id),
ADD CONSTRAINT fk_procedure_pet FOREIGN KEY (procedure_id) REFERENCES procedure(procedure_id);

COMMIT;

ROLLBACK;