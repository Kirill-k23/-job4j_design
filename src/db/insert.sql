insert into users(name)
values ('Ivan');
insert into items(name, users_id)
 values ('item', 1);
insert into roles(name, users_id)
 values ('role', 1);
insert into rules(name)
values ('rule');
insert into rules_roles(rules_id, roles_id)
values (1, 1);
insert into coments(name, items_id)
values ('comment', 1);
insert into attachs(name, items_id)
values ('attach', 1);
insert into states(name, items_id)
 values ('states', 1);
insert into categories(name, items_id)
 values ('categories', 1);