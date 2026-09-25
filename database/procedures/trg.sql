

CREATE OR REPLACE TRIGGER trg_sale_id
BEFORE INSERT ON sales
FOR EACH ROW
BEGIN

  :NEW.sale_id := 'SAL' || LPAD(sale_seq.NEXTVAL, 2, '0');
END;