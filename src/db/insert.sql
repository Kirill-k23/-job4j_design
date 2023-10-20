insert into roles(name)
 values ('role');
insert into users(name, loles_id)
values ('Ivan', 1);
insert into rules(name)
values ('rule');
insert into rules_roles(rules_id, roles_id)
values (1, 1);
insert into states(name)
 values ('states');
insert into categories(name)
 values ('categories');
insert into items(name, users_id, states_id, categories_id)
 values ('item', 1, 1, 1);
insert into coments(name, items_id)
values ('comment', 1);
insert into attachs(name, items_id)
values ('attach', 1);