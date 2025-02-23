CREATE TABLE users (
   user_cin VARCHAR2(50) NOT NULL PRIMARY KEY,
   user_name VARCHAR2(50) NOT NULL,
   user_sal NUMBER(4),
   user_password VARCHAR2(255) NOT NULL 
);

create table medecins (
   med_id       varchar2(50)  not null primary key,
   med_name     varchar2(50) ,
   med_price    number(4,2),
   med_quantity number(5)
);