create table products_proc (
    id serial primary key,
    name_prod varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure delete_prod(p_id int)
language 'plpgsql' as $$
begin
delete from products_proc where products_proc.id = p_id;
end
$$

create or replace function delete_prod()
returns void
language 'plpgsql'
as
$$
    begin
            delete from products where products_proc.count = 0;
    end;
$$;