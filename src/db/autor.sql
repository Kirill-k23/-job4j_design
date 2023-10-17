create table users (
    id serial primary key,
    names text
);
create table roles (
    id serial primary key,
    names text,
    author_id int references users(id)
);