create table users(
id serial primary key,
names text,
roles_id int references roles(id)
);
create table roles(
id serial primary key,
names text
);
create table coments (
id serial primary key,
names text
)
create table rules(
id serial primary key,
nemes text
);
create table items(
id serial primary key,
numbers int
);
create table attachs();
create