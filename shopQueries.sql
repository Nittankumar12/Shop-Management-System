CREATE DATABASE Shop;
use Shop;
CREATE TABLE sweets (
    s_id INT PRIMARY KEY,
    s_name VARCHAR(256),
    s_quantity INT,
    s_cost INT NOT NULL
);
CREATE TABLE shop (
    shop_id INT PRIMARY KEY,
    shop_name VARCHAR(256),
    shop_address VARCHAR(256),
    shop_contact VARCHAR(10),
    shop_password VARCHAR(256)
); 
CREATE TABLE expenses (
    expense_id INT PRIMARY KEY AUTO_INCREMENT,
    sweet_id INT,
    sweet_name VARCHAR(256),
    sweet_quantity INT,
    Total INT,
    FOREIGN KEY (sweet_id) REFERENCES sweets(s_id)
);
CREATE TABLE customer (
    c_id INT PRIMARY KEY AUTO_INCREMENT,
    c_name VARCHAR(256),
    c_contact VARCHAR(10),
    c_spend INT
);
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    c_id INT NOT NULL,
    c_name VARCHAR(256),
    sweet_name VARCHAR(256),
    sweet_quantity INT,
    amount INT,
    FOREIGN KEY (c_id) REFERENCES customer(c_id)
);
ALTER TABLE orders
ADD order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
INSERT INTO shop VALUES(1,"NITTAN","DELHI","+9142135","nittan");

select * from sweets;

