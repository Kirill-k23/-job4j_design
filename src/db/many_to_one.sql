create table students(
id serial primary key,
name varchar(255)
);

create table faculty(
id serial primary key,
neme varchar(255),
students_id int references student(id)
);
