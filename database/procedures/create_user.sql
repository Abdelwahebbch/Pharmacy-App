create or replace procedure create_user (
   cin            in varchar,
   user_name      in varchar,
   user_last_name in varchar,
   user_password  in varchar,
   user_email     in varchar,
   user_phone     in varchar,
   sal            in number
) as
begin
   insert into users values ( cin,
                              user_name,
                              user_last_name,
                              user_password,
                              user_email,
                              user_phone,
                              sal );
exception
   when others then
      dbms_output.put_line('There are a Error please verify the type of parameters ');
end;