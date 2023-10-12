create table tourist(
id serial primary key,
names varchar(255)
);

create table hotels(
id serial primary key,
names text
);

create table tourist_houtels(
id serial primary key,
tourist_id int references tourist(id),
hotels_id int references hotels(id)
);