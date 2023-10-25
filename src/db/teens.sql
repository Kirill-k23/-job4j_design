create table teens(
id_teens serial primary key,
names varchar(255),
gender varchar(255)
);

insert into teens(names, gender)
values ('Ivan', 'Man'), ('Vera', 'Woman'), ('Petr', 'Man'), ('Oleg', 'Man'), ('Lera', 'Woman');

select t.names, t.gender, tt.names, tt.gender
from teens as t cross join teens as tt
where t.gender = 'Man' and tt.gender = 'Woman';