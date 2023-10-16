create table passenger(
id serial primary key,
name varchar(255)
);

create table ticket(
id serial primary key,
number int,
passenger_id unique references passenger(id)
);