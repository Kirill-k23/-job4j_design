create table faunas(
id serial primary key,
names text,
avg_age int,
discovery_date date
);

insert into faunas(names, avg_age, discovery_date) values ('Gupy', 25, '1998-10-01');
insert into faunas(names, avg_age) values ('White_fish', 100);
insert into faunas(names, avg_age, discovery_date) values ('Grizli', 15400, '1892-02-12');
insert into faunas(names, avg_age) values ('Shark', 20000)
select * from faunas;
select * from faunas where discovery_date < '1950-01-01';
select * from faunas where names like '%fish' or names like 'fish%' or names like '%fish%';
select * from faunas where discovery_date is null;
select * from faunas where avg_age > 10000 and avg_age < 21000 order by avg_age desc;