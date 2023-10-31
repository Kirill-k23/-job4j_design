create table products (
id serial primary key,
	name varchar(50),
	produser varchar(50),
	count integer default 0,
	price integer
);

create or replace function discount()
returns trigger as
$$
BEGIN
update products
set price = price - price * 0.2
where count <= 5 AND id = new.id;
return NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger discount_triger
after insert
on products
for each row
execute procedure discount();

select * from products;

insert into products (name, produser, count, price)
values ('product_3', 'producer_3', 8, 115);

insert into products (name, produser, count, price)
values ('product_1', 'producer_1', 3, 50);

insert into products (name, produser, count, price)
values ('product_1', 'producer_1', 10, 50);

create or replace function tax_prod()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.13
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_prod();

	insert into products (name, produser, count, price)
values ('product_4', 'producer_4', 3, 50);

create or replace function before_()
returns trigger as
$$
BEGIN
new.price = new.price * 1.2;
return NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before
before insert
on products
for each row
execute procedure before_();

create table history_of_price (
id serial primary key,
	name varchar(50),
	price integer,
	date timestamp
);

create or replace function history()
    returns trigger as
$$
    BEGIN
      insert into history_of_price (name, price, date)
  values(NEW.name, NEW.price, current_date);
  return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_triger
after insert
on products
for each row
execute procedure history();

insert into products (name, produser, count, price)
values ('product_12', 'producer_12', 10, 100);

select * from history_of_price;