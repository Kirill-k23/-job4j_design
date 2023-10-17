create table users(
id serial primary key,
names text
);
create table roles(
id serial primary key,
names text,
users_id int references users(id)
);
create table rules_roles(
id serial primary key,
rules_id int references rules(id),
roles_id int references roles(id)
);
create table coments (
id serial primary key,
names text,
items_id int references items(id)
)
create table rules(
id serial primary key,
nemes text
);
create table items(
id serial primary key,
users_id int references users(id),
categories_id int references categories(id)
);
create table attachs(
id serial primary key,
name_ text,
items_id int references items(id)
);
create table states(
id serial primary key,
name_ text,
items_id int references items(id)
);
create table categories(
id serial primary key,
name_ text,
items_id int references items(id)
);