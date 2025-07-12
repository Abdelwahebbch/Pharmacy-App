

CREATE OR REPLACE TRIGGER trg_pres_id
BEFORE INSERT ON prescriptions
FOR EACH ROW
BEGIN
 
  :NEW.pres_id := 'PRES' || LPAD(pres_seq.NEXTVAL, 2, '0');
END;

CREATE OR REPLACE TRIGGER trg_med_id
BEFORE INSERT ON medications
FOR EACH ROW
BEGIN

  :NEW.med_id := 'MED' || LPAD(med_seq.NEXTVAL, 2, '0');
END;

CREATE OR REPLACE TRIGGER trg_sale_id
BEFORE INSERT ON sales
FOR EACH ROW
BEGIN

  :NEW.sale_id := 'SAL' || LPAD(sale_seq.NEXTVAL, 2, '0');
END;