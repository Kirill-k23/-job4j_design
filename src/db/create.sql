create table roles(
id serial primary key,
names text
);
create table users(
id serial primary key,
names text,
roles_id int references roles(id)
);
create table rules(
id serial primary key,
names text
);
create table rules_roles(
id serial primary key,
rules_id int references rules(id),
roles_id int references roles(id)
);
create table states(
id serial primary key,
names text
);
create table categories(
id serial primary key,
names text
);
create table items(
id serial primary key,
numbers int,
users_id int references users(id),
states_id int references states(id),
categories_id int references categories(id)
);
create table coments (
id serial primary key,
names text,
items_id int references items(id)
);
create table attachs(
id serial primary key,
names text,
items_id int references items(id)
);