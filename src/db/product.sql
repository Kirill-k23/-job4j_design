create table products(
id serial primary key,
names varchar(255),
type_id int references types(id),
expired_date date,
price int
);

create table types(
id serial primary key,
names varchar(255)
);

insert into types(names)
values ('cheese'), ('milk'), ('bread'), ('meat');

insert into products(names, type_id, expired_date, price)
values ('Russian cheese', 1, '21-10-2023', 400), ('milk', 2, '19-10-2023', 100),
('white bread', 3, '20-10-2023', 50), ('pork', 4, '26-10-2023', 520);
inser into products()

select * from products join types
on type_id = types.id where types.names = 'cheese';

select * from products where names like '%pork%';

select * from products where current_date > expired_date;

select * from products
where price = (select max(price) from products);

select types.names, count(*) from "types"
join products on type_id = types.id
group by "types"."names";

select * from products
join "types" on type_id = "types"."id"
where "types"."names" = 'cheese' or "types"."names" = 'milk';

select types.names, count(*) from "types"
join products on type_id = "types"."id"
group by "types"."names"
having count(*) <10;

select * from products
join "types" on type_id = "types"."id";
