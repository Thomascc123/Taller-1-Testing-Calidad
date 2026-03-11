DELETE FROM customer;
ALTER TABLE customer AUTO_INCREMENT = 1;
INSERT INTO customer (name, last_name, email, phone_number, document_type, document_number, status)
VALUES ('Camilo', 'Perez', 'camilop@gmail.com', '3154238456', 'CEDULA_CIUDADANIA', '1057452245', 'Activo');