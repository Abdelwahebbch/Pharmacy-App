create or replace trigger trg_recent_sale after
   insert or update or delete on sales
   for each row
   declare 
   nb number ; 
begin
   select count(*)
     into nb
     from sales;


end;