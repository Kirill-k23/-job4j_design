create table products (
id serial primary key,
	name varchar(50),
	produser varchar(50),
	count integer default 0,
	price integer
);

create or replace function dis()
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

create trigger trigger
after insert
on products
for each row
execute procedure discount();

select * from products;

drop trigger discount_trigger on products;

alter table products disable trigger discount_trigger;

insert into products (name, produser, count, price)
values ('product_3', 'producer_3', 8, 115);

insert into products (name, produser, count, price)
values ('product_1', 'producer_1', 3, 50);

insert into products (name, produser, count, price)
values ('product_1', 'producer_1', 3, 50);

create or replace function tax_prod()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.13
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_prod();

	insert into products (name, produser, count, price)
values ('product_4', 'producer_4', 3, 50);

create or replace function bef()
returns trigger as
$$
BEGIN
update products
set price = price + price * 0.13
where id = new.id;
return NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger tax_bef
before insert
on products
for each row
execute procedure discount();

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
        update products
      insert into history_of_price (name, price, date)
  values(NEW.name, NEW.price, current_date);
  return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_bef
after insert
on products
for each row
execute procedure discount();

insert into products (name, produser, count, price)
values ('product_12', 'producer_12', 10, 100);

select * from history_of_price;
