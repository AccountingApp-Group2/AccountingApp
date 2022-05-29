INSERT INTO company (created_by,created_time, is_deleted, updated_by, updated_time, address1, address2,email,enabled,establishment_date,phone,representative,title,zip)
VALUES
    (2,'2022-01-05 00:00:00',FALSE,3,'2022-03-05 00:00:00','21 State Street','Boston','anyone@mail.com',TRUE ,'2022-04-05 00:00:00','2223334444','Robert Muller','Apple','02020'),
    (3,'2022-05-06 00:00:00',FALSE,4,'2022-07-05 00:00:00','221 Union Street','Chickago','someone@mail.com',TRUE,'2022-04-08 00:00:00','4854564355','Tom Hanks','Walmart','02022'),
    (3,'2022-05-06 00:00:00',FALSE,4,'2022-07-05 00:00:00','221 Summer Street','Boston','any@mail.com',TRUE,'2022-04-08 00:00:00','4854564355','Mary Poppins','Shell','02021');

INSERT INTO invoice (created_by, created_time, is_deleted, updated_by, updated_time, enabled, invoice_date, invoice_number, invoice_status)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV011', 'PAID'),
       (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV012', 'PENDING'),
       (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', TRUE, '04/15/2022', 'P-INV013', 'PAID');

INSERT INTO product (created_by, created_time, is_deleted, updated_by, updated_time, description, enabled, low_limit_alert, name, new_column, product_status, qty, tax, unit)
VALUES (1, '2022-04-15 00:00:00', FALSE, 1, '2022-05-07 00:00:00', 'computer', TRUE, 15, 'MacBook', 1, 'ACTIVE', 15, 4, 'Pieces'),
       (2, '2022-03-17 00:00:00', FALSE, 2, '2022-05-20 00:00:00', 'sports', TRUE, 7, 'Shoe', 1, 'ACTIVE', 7, 2, 'Pieces'),
       (3, '2022-02-05 00:00:00', FALSE, 3, '2022-04-12 00:00:00', 'TV', TRUE, 43, 'TV', 1, 'ACTIVE', 43, 5, 'Pieces');

INSERT INTO stock_details (i_date, quantity, price, remaining_quantity, product_id)
VALUES ('2022-04-15 00:00:00', 2344, 999.99, 34525423, 3),
       ('2022-01-15 00:00:00', 67, 99.99, 9687, 1),
       ('2022-02-15 00:00:00', 6785, 9.99, 6978, 2);

INSERT INTO invoice_product (created_by, created_time, is_deleted, updated_by, updated_time, name, price, profit, qty, tax, invoice_id, product_id)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name1', 100, 10, 1, 5, 1,1),
       (2, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name2', 200, 15, 2, 8, 2,2),
       (3, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'name3', 300, 20, 3, 10, 3,3);

INSERT INTO payment (created_by, created_time, is_deleted, updated_by, updated_time, amount, institution_id, is_paid, month, year, company_id)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 100.00, '1', true, 'January','01/15/2022',2),
       (2, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 95.09, '2', false, 'May', '05/12/2022',1),
       (3, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 45.87, '3', true, 'June', '06/1/2022',3);

--TODO @Abbos - category
INSERT INTO category (created_by, created_time, is_deleted, updated_by, updated_time, description, enabled)
VALUES (1, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'computer', true),
       (2, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'TV', true ),
       (3, '2022-01-05 00:00:00', FALSE, 1, '2022-01-05 00:00:00', 'sports', true);

INSERT INTO role (name , enabled)
VALUES ('somename',true),
       ('someone',true),
       ('person',false);

INSERT INTO users(created_by, created_time, updated_by, updated_time, company_id, first_name, last_name, role_id,  email, password, phone, enabled, is_deleted)
VALUES (1, '2022-01-05 00:12:00', 1, '2022-01-05 00:20:00', 2, 'Marylin', 'Monro', 3, 'monro@gmail.com', 'monro10', '2063334611', TRUE, FALSE),
       (2, '2022-01-05 00:12:00', 1, '2022-01-05 00:20:00', 1, 'Michael', 'Jackson', 2, 'jackson01@gmail.com', 'jackson10', '2063334612', TRUE, FALSE),
       (3, '2022-01-05 00:12:00', 1, '2022-01-05 00:20:00', 3, 'Paul', 'Florettini', 1, 'florettini@gmail.com',  'smith29', '2063334613', TRUE, FALSE);