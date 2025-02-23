create table users (
   user_cin       varchar2(50) not null primary key,
   user_name      varchar2(50) not null,
   user_last_name varchar2(50) not null,
   user_password  varchar2(255) not null,
   user_email     varchar2(50) not null,
   user_phone     varchar2(50) not null,
   user_sal       number(4)
);


create table medecins (
   med_id       varchar2(50) not null primary key,
   med_name     varchar2(50),
   med_price    number(4,2),
   med_quantity number(5)
);