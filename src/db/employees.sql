create table employees(
id_employees serial primary key,
names varchar(255)
);

create table departments(
departments_id serial primary key,
names varchar(255),
id_employees int references employees(id_employees)
);

insert into employees(names)
values ('employe 1'), ('employe 2'), ('employe 3'), ('employe 4');

insert into departments(names, id_employees)
values ('department 1', 1), ('department 2', 1), ('department 3', 4), ('department 4', 2),
('department 5', null ), ('department 6', 3), ('department 7', null ), ('department 8', 2);

select * from departments left join employees using (id_employees);

select * from departments right join employees using (id_employees);

select * from departments full join employees using (id_employees);

select * from departments cross join employees;

select * from departments left join employees using (id_employees)
where employees.id_employees is null ;
