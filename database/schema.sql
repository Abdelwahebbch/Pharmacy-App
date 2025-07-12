create table users (
   user_cin       varchar2(50) not null primary key,
   user_name      varchar2(50) not null,
   user_last_name varchar2(50) not null,
   user_password  varchar2(255) not null,
   user_email     varchar2(50) not null,
   user_phone     varchar2(50) not null,
   user_sal       number(4)
);


create table medications (
   med_id       varchar2(50) primary key,
   med_name     varchar2(50),
   med_categ    varchar2(50),
   med_price    number(4,2),
   med_quantity number(5),
   med_exp      date
);
create table prescriptions (
   pres_id      varchar2(50) primary key,
   patient_id   varchar2(50) not null,
   patient_name varchar2(50),
   doctor_name  varchar2(50),
   issue_date   date,
   med_exp      date,
   status       varchar2(50),
   medications  varchar2(300),
   constraint fk_id_presc foreign key ( patient_id )
      references patients ( patient_phone )
);

create table patients (
   patient_phone varchar2(50) primary key not null,
   patient_name  varchar2(50),
   family_doc VARCHAR2(30),
   patient_date  date,
   patient_note  varchar2(500)
);




create table sales (
   sale_id       varchar2(50) primary key,
   med_name      varchar2(50),
   sale_quantity number,
   sale_price    number,
   sale_date     date,
   sale_categ    varchar2(50),
   constraint fk_med_name foreign key ( med_name )
      references medications ( med_id )
);


-- create sequence med_seq start with 1 increment by 1 nocache nocycle;
-- create sequence patient_seq start with 1 increment by 1 nocache nocycle;
-- create sequence pres_seq start with 1 increment by 1 nocache nocycle;