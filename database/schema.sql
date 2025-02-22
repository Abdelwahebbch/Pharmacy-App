create table users (
   user_cin  varchar2(50) not null primary key,
   user_name varchar2(50)  not null,
   user_sal  number(4)
);

create table medecins (
   med_id       varchar2(50)  not null primary key,
   med_name     varchar2(50) ,
   med_price    number(4,2),
   med_quantity number(5)
);