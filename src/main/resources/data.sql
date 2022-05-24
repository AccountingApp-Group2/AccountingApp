INSERT INTO invoice (created_by, created_time, is_deleted, updated_by, updated_time, enabled, invoice_date, invoice_number, invoice_status)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV011', 'PAID'),
       (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV012', 'PENDING'),
       (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV013', 'PAID');


-- TODO @Bahrom - add product data

-- TODO @Vitaly fix invoice_product to include product id
INSERT INTO invoice_product (created_by, created_time, is_deleted, updated_by, updated_time, name, price, profit, qty, tax, invoice_id)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name1', 100, 10, 1, 5, 1),
       (2, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name2', 200, 15, 2, 8, 2),
       (3, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name3', 300, 20, 3, 10, 3);

