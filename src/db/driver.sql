create table car(
id serial primary key,
number int,
color text
);
create table driver(
id serial primary key,
names text,
car_id int references car(id) unique
);
insert into car(number, color)
values (3221, 'Black');
insert into car(number, color)
values (2134, 'White');
insert into car(number, color)
values (4212, 'Green');
insert into driver(names, car_id)
values ('Ivan', 1);
insert into driver(names, car_id)
values ('Misha', 2);
insert into driver(names, car_id)
values ('Oleg', 3);
insert into driver(names)
values ('Viktor');
insert into driver(names)
values ('Alex');
select * from driver inner
join car on driver.car_id = car.id;
select d.names, c.number, c.color
from driver as d join car as c on d.car_id = c.id;
select d.names as Имя, c.number as Номер, c.color as Цвет
from driver as d join car as c on d.car_id = c.id;
select d.names as Driver_name, c.number as Car_number, c.color as Color
from driver as d join car as c on d.car_id = c.id;
