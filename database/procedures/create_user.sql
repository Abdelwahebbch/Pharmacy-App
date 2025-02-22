create or replace procedure create_user (
   user_name in varchar,
   cin  in varchar,
   sal  in number
) as
begin
   insert into users values ( cin,
                              user_name,
                              sal );
exception
   when others then
      dbms_output.put_line('There are a Error please verify the type of parameters ');
end;