CREATE DATABASE Shop;
use Shop;
CREATE TABLE sweets (
    s_id INT PRIMARY KEY,
    s_name VARCHAR(256),
    s_quantity INT,
    s_cost INT NOT NULL
);
SHOW TABLES;
DESC sweets;
DROP  TABLE shop;
CREATE TABLE shop (
    shop_id INT PRIMARY KEY,
    shop_name VARCHAR(256),
    shop_address VARCHAR(256),
    shop_contact BIGINT NOT NULL,
    shop_email VARCHAR(256)
); 
DESC shop;
