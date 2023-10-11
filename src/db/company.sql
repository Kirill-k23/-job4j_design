create table company(
id serial primary key,
name varchar(150),
adress text,
number int
);
insert into company (name, adress, number)
values ('Google', 'Colifornia', 2000);
update company set number = 2020;
delete from company;
