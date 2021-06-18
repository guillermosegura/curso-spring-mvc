DROP TABLE payments IF EXISTS;
DROP TABLE orderdetails IF EXISTS;
DROP TABLE orders IF EXISTS;
DROP TABLE customers IF EXISTS;
DROP TABLE employees IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE productlines IF EXISTS;
DROP TABLE offices IF EXISTS;

CREATE TABLE customers (
  customerNumber int(11) IDENTITY NOT NULL ,
  customerName varchar(50) NOT NULL,
  contactLastName varchar(50) NOT NULL,
  contactFirstName varchar(50) NOT NULL,
  phone varchar(50) NOT NULL,
  addressLine1 varchar(50) NOT NULL,
  addressLine2 varchar(50) DEFAULT NULL,
  city varchar(50) NOT NULL,
  state varchar(50) DEFAULT NULL,
  postalCode varchar(15) DEFAULT NULL,
  country varchar(50) NOT NULL,
  salesRepEmployeeNumber int(11) DEFAULT NULL,
  creditLimit decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (customerNumber) 
);

CREATE TABLE employees (
  employeeNumber int(11) IDENTITY NOT NULL,
  lastName varchar(50) NOT NULL,
  firstName varchar(50) NOT NULL,
  extension varchar(10) NOT NULL,
  email varchar(100) NOT NULL,
  officeCode varchar(10) NOT NULL,
  reportsTo int(11) DEFAULT NULL,
  jobTitle varchar(50) NOT NULL,
  PRIMARY KEY (employeeNumber)
);


CREATE TABLE offices (
  officeCode varchar(10) NOT NULL,
  city varchar(50) NOT NULL,
  phone varchar(50) NOT NULL,
  addressLine1 varchar(50) NOT NULL,
  addressLine2 varchar(50) DEFAULT NULL,
  state varchar(50) DEFAULT NULL,
  country varchar(50) NOT NULL,
  postalCode varchar(15) NOT NULL,
  territory varchar(10) NOT NULL,
  PRIMARY KEY (officeCode)
);


CREATE TABLE orders (
  orderNumber int(11) IDENTITY NOT NULL,
  orderDate date NOT NULL,
  requiredDate date NOT NULL,
  shippedDate date DEFAULT NULL,
  status varchar(15) NOT NULL,
  comments text,
  customerNumber int(11) NOT NULL,
  PRIMARY KEY (orderNumber) 
);

CREATE TABLE orderdetails (
  orderNumber int(11) NOT NULL,
  productCode varchar(15) NOT NULL,
  quantityOrdered int(11) NOT NULL,
  priceEach decimal(10,2) NOT NULL,
  orderLineNumber smallint(6) NOT NULL,
  PRIMARY KEY (orderNumber,productCode)
);


CREATE TABLE payments (
  customerNumber int(11) NOT NULL,
  checkNumber varchar(50) NOT NULL,
  paymentDate date NOT NULL,
  amount decimal(10,2) NOT NULL,
  PRIMARY KEY (customerNumber,checkNumber)
);

CREATE TABLE productlines (
  productLine varchar(50) NOT NULL,
  textDescription varchar(4000) DEFAULT NULL,
  htmlDescription mediumtext,
  image mediumblob,
  PRIMARY KEY (productLine)
);

CREATE TABLE products (
  productCode varchar(15) NOT NULL,
  productName varchar(70) NOT NULL,
  productLine varchar(50) NOT NULL,
  productScale varchar(10) NOT NULL,
  productVendor varchar(50) NOT NULL,
  productDescription text NOT NULL,
  quantityInStock smallint(6) NOT NULL,
  buyPrice decimal(10,2) NOT NULL,
  MSRP decimal(10,2) NOT NULL,
  PRIMARY KEY (productCode) 
);

ALTER TABLE customers
ADD CONSTRAINT customers_ibfk_1 
FOREIGN KEY (salesRepEmployeeNumber) 
REFERENCES employees (employeeNumber);

ALTER TABLE employees
ADD CONSTRAINT employees_ibfk_1 
FOREIGN KEY (reportsTo) 
REFERENCES employees (employeeNumber);

ALTER TABLE employees
ADD CONSTRAINT employees_ibfk_2 
FOREIGN KEY (officeCode) 
REFERENCES offices (officeCode);

ALTER TABLE orderdetails
ADD CONSTRAINT orderdetails_ibfk_1 
FOREIGN KEY (orderNumber) 
REFERENCES orders (orderNumber);

ALTER TABLE orderdetails
ADD CONSTRAINT orderdetails_ibfk_2 
FOREIGN KEY (productCode) 
REFERENCES products (productCode);

ALTER TABLE orders
ADD CONSTRAINT orders_ibfk_1 
FOREIGN KEY (customerNumber)
REFERENCES customers (customerNumber);

ALTER TABLE payments
ADD CONSTRAINT payments_ibfk_1 
FOREIGN KEY (customerNumber) 
REFERENCES customers (customerNumber);

ALTER TABLE products
ADD CONSTRAINT products_ibfk_1 
FOREIGN KEY (productLine) 
REFERENCES productlines (productLine);