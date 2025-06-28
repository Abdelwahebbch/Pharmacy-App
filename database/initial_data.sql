BEGIN
   FOR i IN 1..50 LOOP
      INSERT INTO prescriptions (
         pres_id, patient_name, doctor_name, issue_date, med_exp, status, medications
      ) VALUES (
         'PRES_' || TO_CHAR(i, 'FM00'),
         'Patient_' || TO_CHAR(i, 'FM00'),
         'Dr_' || CHR(65 + MOD(i, 26)),
         SYSDATE - MOD(i * 7, 60),         -- issue_date: dates récentes
         SYSDATE + MOD(i * 15, 365),       -- med_exp: expiration future
         CASE MOD(i, 3)
            WHEN 0 THEN 'Malade'
            WHEN 1 THEN 'Guéri'
            ELSE 'Suivi'
         END,
         'Paracetamol 500mg, Ibuprofen 200mg, Vitamine C'
      );
   END LOOP;
   COMMIT;
END;
/
