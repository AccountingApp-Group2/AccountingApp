INSERT INTO invoice (created_by, created_time, is_deleted, updated_by, updated_time, enabled, invoice_date, invoice_number, invoice_status)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV011', 'PAID'),
       (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV012', 'PENDING'),
       (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV013', 'PAID');


INSERT INTO product (created_by, created_time, is_deleted, updated_by, updated_time, description, enabled, low_limit_alert, name, new_column, product_status, qty, tax, unit)
VALUES (1, '2022-04-15 00:00:00', FALSE, 1, '2022-05-07 00:00:00', 'computer', TRUE, 15, 'MacBook', 1, 'ACTIVE', 15, 4, 'Pieces'),
       (2, '2022-03-17 00:00:00', FALSE, 2, '2022-05-20 00:00:00', 'sports', TRUE, 7, 'Shoe', 1, 'ACTIVE', 7, 2, 'Pieces'),
       (3, '2022-02-05 00:00:00', FALSE, 3, '2022-04-12 00:00:00', 'TV', TRUE, 43, 'TV', 1, 'ACTIVE', 43, 5, 'Pieces');


-- TODO @Vitaly fix invoice_product to include product id
INSERT INTO invoice_product (created_by, created_time, is_deleted, updated_by, updated_time, name, price, profit, qty, tax, invoice_id)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name1', 100, 10, 1, 5, 1),
       (2, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name2', 200, 15, 2, 8, 2),
       (3, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name3', 300, 20, 3, 10, 3);

--TODO @Abbos - category
INSERT INTO category (created_by, created_time, is_deleted, updated_by, updated_time, description, enabled)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'computer', true),
       (2, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'TV', true ),
       (3, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'sports', true);


INSERT INTO users(created_by, created_time, updated_by, updated_time, first_name, last_name, email, password, phone, enabled, is_deleted)
    --TODO Gulmira and Muhabbat need to create below entities - company_id, role_id)
VALUES (10, '2022-01-05 00:12:00', 10, '2022-01-05 00:20:00', 'Marylin', 'Monro', 'monro@gmail.com', 'monro10', '2063334611', TRUE, FALSE),
       (20, '2022-01-05 00:12:00', 20, '2022-01-05 00:20:00', 'Michael', 'Jackson', 'jackson01@gmail.com', 'jackson10', '2063334612', TRUE, FALSE),
       (30, '2022-01-05 00:12:00', 30, '2022-01-05 00:20:00', 'Paul', 'Smith', 'smith@gmail.com', 'smith29', '2063334613', TRUE, FALSE);