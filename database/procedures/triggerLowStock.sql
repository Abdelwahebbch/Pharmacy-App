CREATE OR REPLACE TRIGGER trg_low_stock
AFTER UPDATE OF med_quantity ON medecins
FOR EACH ROW
WHEN (NEW.med_quantity < 10 AND NEW.med_quantity < OLD.med_quantity)
BEGIN
    INSERT INTO low_stock_log (med_id, med_name, quantity)
    VALUES (:NEW.med_id, :NEW.med_name, :NEW.med_quantity);
END;
/
