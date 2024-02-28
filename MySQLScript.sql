CREATE USER 'egsadmin'@'localhost' IDENTIFIED BY 'egsadmin';
CREATE DATABASE egs;
GRANT ALL PRIVILEGES ON egs.* TO 'egsadmin'@'localhost';
ALTER USER 'egsadmin'@'localhost' DEFAULT DATABASE egs;
CREATE TABLE customer (
    customerref BIGINT PRIMARY KEY,
    customername VARCHAR(255),
    addressline1 VARCHAR(255),
    addressline2 VARCHAR(255),
    town VARCHAR(255),
    county VARCHAR(255),
    country VARCHAR(255),
    postcode VARCHAR(10)
);
