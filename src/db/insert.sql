insert into roles(names)
 values ('role');
insert into users(names, roles_id)
values ('Ivan', 1);
insert into rules(names)
values ('rule');
insert into rules_roles(rules_id, roles_id)
values (1, 1);
insert into states(names)
 values ('states');
insert into categories(names)
 values ('categories');
insert into items(numbers, users_id, states_id, categories_id)
 values (1, 1, 1, 1);
insert into coments(names, items_id)
values ('comment', 1);
insert into attachs(names, items_id)
values ('attach', 1);