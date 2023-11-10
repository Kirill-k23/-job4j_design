create table employees_test_isolation (
id serial primary key,
name_emp text,
age int,
salary int
);

insert into employees_test_isolation (name_emp, age, salary)
values ('Sam', 24, 2100), ('Pit', 29, 2130), ('Mary', 25, 2300), ('Bob', 27, 2450);

select * from employees_test_isolation;
select sum(salary) from employees_test_isolation;
update employees_test_isolation set salary = 2550 where name_emp = 'Bob';
begin transaction isolation level read uncommitted;

commit;

begin transaction isolation level read uncommitted;
select sum(salary) from employees_test_isolation;
update employees_test_isolation set salary = 2600 where name_emp = 'Mary';
commit;