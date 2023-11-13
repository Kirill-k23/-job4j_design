CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers(first_name, last_name, age, country)
VALUES ('Ivan', 'Ivanov', 25, 'Russia'), ('Bob', 'Smit', 27, 'USA'), ('Karl', 'Marx', 30, 'Germany');

SELECT * FROM customers
WHERE age = (select min(age) FROM customers);


CREATE TABLE orders_cust(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);
INSERT INTO orders_cust(amount, customer_id)
VALUES (10, 1), (30, 3);

SELECT * FROM customers WHERE id NOT IN (SELECT customer_id from orders_cust);
