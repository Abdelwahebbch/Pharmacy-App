create or replace trigger trg_recent_sale after
   insert or update or delete on sales
   for each row
begin
   select count(*)
     into nb
     from sales;


end;