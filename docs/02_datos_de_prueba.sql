-- 2do script: Datos de prueba
USE tfi_grupo147_v2;

-- SeguroVehicular
INSERT INTO SeguroVehicular (eliminado, aseguradora, nroPoliza, cobertura, vencimiento)
VALUES
(FALSE, 'La Mercantil Andina', 'POL-0001', 'RC', '2026-05-30'),
(FALSE, 'Aseguradora Federal Argentina', 'POL-0002', 'TERCEROS', '2026-09-15'),
(FALSE, 'La Segunda', 'POL-0003', 'TODO_RIESGO', '2027-01-10'),
(FALSE, 'Providencia Seguros', 'POL-0004', 'TERCEROS', '2026-12-01'),
(FALSE, 'Federación Patronal', 'POL-0005', 'RC', '2027-03-25'),
(FALSE, 'SanCor Seguros', 'POL-0006', 'TODO_RIESGO', '2027-11-20'),
(FALSE, 'La Mercantil Andina', 'POL-0007', 'TERCEROS', '2028-02-28'),
(FALSE, 'Providencia Seguros', 'POL-0008', 'RC', '2027-08-12'),
(FALSE, 'Federación Patronal', 'POL-0009', 'TODO_RIESGO', '2026-07-07'),
(FALSE, 'SanCor Seguros', 'POL-0010', 'RC', '2028-04-18');

-- Vehiculo
INSERT INTO Vehiculo (eliminado, dominio, marca, modelo, anio, nroChasis, seguro_id)
VALUES
(FALSE, 'AA123AA', 'Toyota', 'Corolla', 2020, 'CHASIS-001', 1),
(FALSE, 'BB234BB', 'Ford', 'Focus', 2018, 'CHASIS-002', 2),
(FALSE, 'CC345CC', 'Volkswagen', 'Gol', 2022, 'CHASIS-003', 3),
(FALSE, 'DD456DD', 'Renault', 'Kangoo', 2019, 'CHASIS-004', 4),
(FALSE, 'EE567EE', 'Peugeot', '208', 2021, 'CHASIS-005', 5),
(FALSE, 'FF678FF', 'Fiat', 'Cronos', 2023, 'CHASIS-006', 6),
(FALSE, 'GG789GG', 'Chevrolet', 'Onix', 2017, 'CHASIS-007', 7),
(FALSE, 'HH890HH', 'Nissan', 'Versa', 2022, 'CHASIS-008', 8),
(FALSE, 'II901II', 'Honda', 'Civic', 2020, 'CHASIS-009', 9),
(FALSE, 'JJ012JJ', 'Toyota', 'Yaris', 2021, 'CHASIS-010', 10);

SELECT * FROM SeguroVehicular;
SELECT * FROM Vehiculo