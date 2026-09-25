INSERT INTO prescriptions 
(pres_id, patient_id, patient_name, doctor_name, issue_date, med_exp, status, medications) 
VALUES
('P001', '2010000001', 'Ali Ben Salah', 'Dr. Karim', TO_DATE('2023-01-10', 'YYYY-MM-DD'), TO_DATE('2023-02-10', 'YYYY-MM-DD'), 'Active', 'Paracetamol 500mg, 2x/day'),
('P002', '2010000002', 'Mouna Trabelsi', 'Dr. Leila', TO_DATE('2023-02-05', 'YYYY-MM-DD'), TO_DATE('2023-03-05', 'YYYY-MM-DD'), 'Expired', 'Metformin 850mg, 1x/day'),
('P003', '2010000003', 'Houssem Chebbi', 'Dr. Amine', TO_DATE('2023-03-12', 'YYYY-MM-DD'), TO_DATE('2023-04-12', 'YYYY-MM-DD'), 'Active', 'Ibuprofen 400mg, 3x/day'),
('P004', '2010000004', 'Sami Gharbi', 'Dr. Karim', TO_DATE('2023-04-01', 'YYYY-MM-DD'), TO_DATE('2023-05-01', 'YYYY-MM-DD'), 'Cancelled', 'Vitamin D, 1x/week'),
('P005', '2010000005', 'Rania Mansour', 'Dr. Leila', TO_DATE('2023-04-20', 'YYYY-MM-DD'), TO_DATE('2023-06-20', 'YYYY-MM-DD'), 'Active', 'Amoxicillin 500mg, 3x/day'),
('P006', '2010000006', 'Firas Haddad', 'Dr. Amine', TO_DATE('2023-05-10', 'YYYY-MM-DD'), TO_DATE('2023-06-10', 'YYYY-MM-DD'), 'Expired', 'Omeprazole 20mg, 1x/day'),
('P007', '2010000007', 'Nadia Kallel', 'Dr. Karim', TO_DATE('2023-06-02', 'YYYY-MM-DD'), TO_DATE('2023-07-02', 'YYYY-MM-DD'), 'Active', 'Cetirizine 10mg, 1x/day'),
('P008', '2010000008', 'Omar Chouchene', 'Dr. Leila', TO_DATE('2023-06-15', 'YYYY-MM-DD'), TO_DATE('2023-07-15', 'YYYY-MM-DD'), 'Active', 'Aspirin 100mg, 1x/day'),
('P009', '2010000009', 'Salma Bouzid', 'Dr. Amine', TO_DATE('2023-07-01', 'YYYY-MM-DD'), TO_DATE('2023-08-01', 'YYYY-MM-DD'), 'Cancelled', 'Iron supplement, 2x/day'),
('P010', '2010000010', 'Walid Jaziri', 'Dr. Karim', TO_DATE('2023-07-20', 'YYYY-MM-DD'), TO_DATE('2023-09-20', 'YYYY-MM-DD'), 'Active', 'Diclofenac 50mg, 2x/day');
