create or replace procedure create_medecin (
   id       varchar,
   med_name     varchar,
   price    number,
   quantity number
) as
begin
   insert into medecins values ( id,
                                 med_name,
                                 price,
                                 quantity );

exception
   when others then
      dbms_output.put_line('There are a Error please verify the type of parameters ');
end;