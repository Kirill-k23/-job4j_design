create table car_bodies(
body_id serial primary key,
name_body varchar(255)
);

create table car_engines(
engine_id serial primary key,
name_engine varchar(255)
);

create table car_transmissions(
transmission_id serial primary key,
name_transmission varchar(255)
);

create table cars(
car_id serial primary key,
name_car varchar(255),
body_id int references car_bodies(body_id),
engine_id int references car_engines(engine_id),
transmission_id int references car_transmissions(transmission_id)
);

insert into car_bodies(name_body)
values ('body 1'), ('body 2'), ('body 3'), ('body 4');

insert into car_engines(name_engine)
values ('V 4'), ('V 6'), ('V 8'), ('V 12');

insert into car_transmissions(name_transmission)
values ('transmission 1'), ('transmission 2'), ('transmission 3'), ('transmission 4');

insert into cars(name_car, body_id, engine_id, transmission_id)
values ('Volvo', 1, 2, 1), ('Lada', 2, null , 3), ('BMW', null, 3, 2), ('Tesla', 3, 2, null ),
('GM', 1, 1, null );

select * from cars
full join car_bodies using (body_id);

select name_body from car_bodies
 left join cars using (body_id)
where cars.body_id is null ;

select name_engine from car_engines
 left join cars using (engine_id)
where cars.engine_id is null ;

select name_transmission from car_transmissions
 left join cars using (transmission_id)
where cars.transmission_id is null ;

create view body_null as
select name_body from car_bodies
 left join cars using (body_id)
where cars.body_id is null

select * from body_null;