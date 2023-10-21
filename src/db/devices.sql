create table devices(
    id serial primary key,
    names varchar(255),
    price decimal
);
create table people(
id serial primary key,
names varchar(255)
);
create table devices_people(
id serial primary key,
device_id int references devices(id),
people_id int references people(id)
);
insert into devices(names, price)
values ('Phone', 1500), ('TV', 3000), ('Watch', 500);
insert into people(names)
values ('Ivan'), ('Vera'), ('Igor');
insert into devices_people(device_id, people_id)
values (1, 2), (2, 3), (3, 1);

select avg(price) from devices;

select people_id, avg(price)
from devices join devices_people
on device_id = devices.id
group by people_id;

select people_id, avg(price)
from devices join devices_people
on device_id = devices.id
group by people_id
having avg(price) > 2000;