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
   med_categ    varchar2(50),
   med_price    number(4,2),
   med_quantity number(5),
   med_exp      date
);
create table prescriptions  (
   pres_id      varchar2(50) not null primary key,
   patient_name varchar2(50),
   doctor_name  varchar2(50),
   issue_date   date,
   med_exp      date,
   status varchar2(50),
   medications varchar2(300)
);

create table sales (
   sel_id    number primary key,
   sel_price number
);

CREATE SEQUENCE med_is_seq START with 1 increment by 1 NOCACHE NOCYCLE ; 
CREATE SEQUENCE pres_is_seq START with 1 increment by 1 NOCACHE NOCYCLE ; 