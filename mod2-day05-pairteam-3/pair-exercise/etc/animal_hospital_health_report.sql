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

CREATE TABLE procedure (
procedure_id serial,
description varchar(100) NOT NULL,
date varchar(11) NOT NULL,
pet_id int NOT NULL,
price money NOT NULL,

CONSTRAINT pk_procedure PRIMARY KEY(procedure_id)
);

CREATE TABLE report(
report_id serial,
pet_id int NOT NULL,
customer_id int NOT NULL,
procedure_id int NOT NULL,

CONSTRAINT pk_report PRIMARY KEY(report_id)
);

CREATE TABLE pet_report(
report_id int NOT NULL,
pet_id int NOT NULL,

CONSTRAINT pk_pet_report PRIMARY KEY(report_id, pet_id)
);

CREATE TABLE customer_report(
report_id int NOT NULL,
customer_id int NOT NULL,

CONSTRAINT pk_customer_report PRIMARY KEY(report_id, customer_id)
);

CREATE TABLE procedure_report(
report_id int NOT NULL,
procedure_id int NOT NULL,

CONSTRAINT pk_procedure_report PRIMARY KEY(report_id, procedure_id)
);

/*Alter Statements*/

BEGIN TRANSACTION;

ALTER TABLE pet_report
ADD CONSTRAINT fk_pet_report FOREIGN KEY (report_id) REFERENCES report(report_id),
ADD CONSTRAINT fk_report_pet FOREIGN KEY (pet_id) REFERENCES pet(pet_id);

ALTER TABLE procedure_report
ADD CONSTRAINT fk_report_procedure FOREIGN KEY (report_id) REFERENCES report(report_id),
ADD CONSTRAINT fk_procedure_report FOREIGN KEY (procedure_id) REFERENCES procedure(procedure_id);

ALTER TABLE customer_report
ADD CONSTRAINT fk_customer_report FOREIGN KEY (report_id) REFERENCES report(report_id),
ADD CONSTRAINT fk_report_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id);

ALTER TABLE report
ADD CONSTRAINT fk_pet FOREIGN KEY (pet_id) REFERENCES pet(pet_id),
ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
ADD CONSTRAINT fk_procedure FOREIGN KEY (procedure_id) REFERENCES procedure(procedure_id);

COMMIT;

ROLLBACK;