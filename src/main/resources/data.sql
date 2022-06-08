INSERT INTO company (created_by,created_time, is_deleted, updated_by, updated_time, address1, address2,company_status, email,enabled,establishment_date,phone,representative,state,title,zip)
VALUES (1,'2022-05-01 00:00:00',FALSE,1,'2022-05-01 00:00:00','Street1 ','House Nu: 1','ENABLED','root@root.com',TRUE ,'2020-01-01 00:00:00','3453431234','Representative Root','ALABAMA','Title1','35242'),
       (1,'2022-05-02 00:00:00',FALSE,1,'2022-05-02 00:00:00','Street2 ','House Nu: 2','ENABLED','admin1@admin.com',TRUE ,'2020-01-01 00:00:00','7658769012','Admin1 AdminLName1','ARIZONA','Title2','38704'),
       (1,'2022-05-03 00:00:00',FALSE,1,'2022-05-03 00:00:00','Street3 ','House Nu: 3','ENABLED','admin1@admin.com',TRUE ,'2020-01-01 00:00:00','3451236789','Admin2 AdminLName2','FLORIDA','Title3','33027');

INSERT INTO client_vendor (created_by, created_time, is_deleted, updated_by, updated_time, company_name, phone, email, address, zip_code, enabled, type, company_id, state_id)
VALUES (2, '2022-05-02 00:00:00', FALSE, 2, '2022-05-01 00:00:00', 'Company1', '1234567890', 'company2@email.com', 'Street 1.', '35242', TRUE, 'CLIENT', 1, 'ALABAMA'),
       (2, '2022-05-02 00:00:00', FALSE, 2, '2022-05-02 00:00:00', 'Company2', '0987654321', 'company2@email.com', 'Street 2', '38704', TRUE, 'VENDOR', 2, 'ARIZONA'),
       (3, '2022-05-03 00:00:00', FALSE, 3, '2022-05-03 00:00:00', 'Company3', '7894561230', 'company3@email.com', 'Street 3', '33027', TRUE, 'CLIENT', 3, 'FLORIDA');

INSERT INTO category (created_by, created_time, is_deleted, updated_by, updated_time, description, enabled,company_id)
VALUES (2, '2022-05-11 00:00:00', FALSE, 2, '2022-05-11 00:00:00', 'Electronics', true,2),
       (2, '2022-05-11 00:00:00', FALSE, 2, '2022-05-11 00:00:00', 'TV', true ,2),
       (3, '2022-05-11 00:00:00', FALSE, 3, '2022-05-11 00:00:00', 'Sports', true,3);


INSERT INTO invoice (created_by, created_time, is_deleted, updated_by, updated_time, enabled, invoice_date, invoice_number, invoice_status,invoice_type,sptable_id,company_id)
VALUES (1, '2022-05-16 00:00:00', FALSE, 1, '2022-05-16 00:00:00', TRUE, '05/16/2022', 'P-INV011', 'APPROVED', 'PURCHASE',2,2),
       (1, '2022-05-16 00:00:00', FALSE, 1, '2022-05-16 00:00:00', TRUE, '05/17/2022', 'P-INV012', 'PENDING', 'SALE',2,2),
       (1, '2022-05-16 00:00:00', FALSE, 1, '2022-05-16 00:00:00', TRUE, '05/18/2022', 'P-INV013', 'PENDING', 'SALE',2,2),
       (1, '2022-05-16 00:00:00', FALSE, 1, '2022-05-16 00:00:00', TRUE, '05/19/2022', 'P-INV014', 'APPROVED', 'PURCHASE',2,2),
       (1, '2022-05-16 00:00:00', FALSE, 1, '2022-05-16 00:00:00', TRUE, '05/20/2022', 'P-INV015', 'PENDING', 'PURCHASE',3,3);

INSERT INTO product (created_by, created_time, is_deleted, updated_by, updated_time, description, enabled, low_limit_alert, name, new_column, product_status, qty, tax, unit,category_id,company_id)
VALUES (4, '2022-05-15 00:00:00', FALSE, 4, '2022-05-15 00:00:00', 'TV', TRUE, 100, 'TV', 1, 'ACTIVE', 50, 8, 'PCS',1,2),
       (4, '2022-05-15 00:00:00', FALSE, 4, '2022-05-15 00:00:00', 'Computer', TRUE, 0, 'MacBook', 1, 'INACTIVE', 50, 8, 'PCS',2,2),
       (8, '2022-05-15 00:00:00', FALSE, 8, '2022-05-15 00:00:00', 'Sports', TRUE, 10, 'Shoes', 1, 'ACTIVE', 150, 10, 'PCS',3,3);

INSERT INTO stock_details (i_date, quantity, price, remaining_quantity, product_id)
VALUES ('2022-05-15 00:00:00', 50, 999.99, 50, 1),
       ('2022-05-15 00:00:00', 50, 1999.99, 50, 2),
       ('2022-05-15 00:00:00', 150, 99.99, 50, 3);

INSERT INTO invoice_product (created_by, created_time, is_deleted, updated_by, updated_time, name, price, profit, qty, tax, invoice_id, product_id)
VALUES (4, '2022-05-16 00:00:00', FALSE, 4, '2022-05-16 00:00:00', 'TV',        1000, 10,  5, 8, 1, 1),
       (4, '2022-05-16 00:00:00', FALSE, 4, '2022-05-16 00:00:00', 'MacBook',   2000, 10,  5, 2, 1, 2),
       (4, '2022-05-16 00:00:00', FALSE, 4, '2022-05-16 00:00:00', 'MacBook',   2000, 199, 1, 2, 2, 2),
       (4, '2022-05-16 00:00:00', FALSE, 4, '2022-05-16 00:00:00', 'MacBook',   2000, 199, 1, 2, 3, 2),
       (4, '2022-05-16 00:00:00', FALSE, 4, '2022-05-16 00:00:00', 'MacBook',   2000, 199, 1, 2, 4, 2),
       (4, '2022-05-16 00:00:00', FALSE, 4, '2022-05-16 00:00:00', 'MacBook',   2000, 199, 1, 2, 4, 2),
       (8, '2022-05-16 00:00:00', FALSE, 8, '2022-05-16 00:00:00', 'Shoes',     1000, 199, 10, 10, 5, 3);

INSERT INTO payment (created_by, created_time, is_deleted, updated_by, updated_time, amount, institution_id, is_paid, month, year, company_id)
VALUES (4, '2022-05-16 00:00:00', FALSE, 4, '2022-05-16 00:00:00', 999.99, '1', true, 'MAY','05/15/2022',2),
       (4, '2022-05-16 00:00:00', FALSE, 4, '2022-05-16 00:00:00', 1999.99, '2', true, 'MAY', '05/16/2022',2),
       (8, '2022-05-16 00:00:00', FALSE, 8, '2022-05-16 00:00:00', 999.90, '3', true, 'MAY', '05/16/2022',3);





INSERT INTO role (enabled, name)
VALUES (TRUE, 'Root'),
       (TRUE, 'Admin'),
       (TRUE, 'Manager'),
       (TRUE, 'Employee');
INSERT INTO users (created_by, created_time, is_deleted, updated_by, updated_time, email, enabled, first_name, last_name, password, phone, user_status, company_id, role_id)
VALUES (1, '2022-05-01 00:00:00', FALSE, 1, '2022-05-01 00:00:00', 'root@root.com', TRUE, 'Root', 'RootSurname', 'Abc1', '0000000000','ACTIVE', 1, 1),
       (1, '2022-05-02 00:00:00', FALSE, 1, '2022-05-02 00:00:00', 'admin1@admin.com', TRUE, 'Admin1', 'AdminLName1', 'Abc1', '0000000001', 'ACTIVE', 2, 2),
       (1, '2022-05-03 00:00:00', FALSE, 1, '2022-05-03 00:00:00', 'admin2@admin.com', TRUE, 'Admin2', 'AdminLName2', 'Abc1', '0000000002', 'ACTIVE', 3, 2),
       (2, '2022-05-03 00:00:00', FALSE, 2, '2022-05-03 00:00:00', 'manager1@admin.com', TRUE, 'Manager1', 'ManagerLName1', 'Abc1', '0000000003', 'ACTIVE', 2, 3),
       (2, '2022-05-03 00:00:00', FALSE, 2, '2022-05-03 00:00:00', 'manager2@admin.com', TRUE, 'Manager2', 'ManagerLName2', 'Abc1', '0000000004', 'ACTIVE', 2, 3),
       (3, '2022-05-03 00:00:00', FALSE, 3, '2022-05-03 00:00:00', 'manager3@admin.com', TRUE, 'Manager3', 'ManagerLName3', 'Abc1', '0000000005', 'ACTIVE', 3, 3),
       (3, '2022-05-03 00:00:00', FALSE, 3, '2022-05-03 00:00:00', 'manager4@admin.com', TRUE, 'Manager4', 'ManagerLName4', 'Abc1', '0000000006', 'ACTIVE', 3, 3),
       (2, '2022-05-04 00:00:00', FALSE, 2, '2022-05-04 00:00:00', 'employee1@employee.com', TRUE, 'Employee1', 'EmployeeLName1', 'Abc1', '0000000007', 'ACTIVE', 2, 4),
       (2, '2022-05-04 00:00:00', FALSE, 2, '2022-05-04 00:00:00', 'employee2@employee.com', TRUE, 'Employee2', 'EmployeeLName2', 'Abc1', '0000000008', 'ACTIVE', 2, 4),
       (2, '2022-04-04 00:00:00', FALSE, 2, '2022-05-04 00:00:00', 'employee3@employee.com', TRUE, 'Employee3', 'EmployeeLName3', 'Abc1', '0000000009', 'ACTIVE', 2, 4),
       (3, '2022-05-05 00:00:00', FALSE, 3, '2022-05-04 00:00:00', 'employee4@employee.com', TRUE, 'Employee4', 'EmployeeLName4', 'Abc1', '0000000010', 'ACTIVE', 3, 4),
       (3, '2022-05-05 00:00:00', FALSE, 3, '2022-05-04 00:00:00', 'employee5@employee.com', TRUE, 'Employee5', 'EmployeeLName5', 'Abc1', '0000000011', 'ACTIVE', 3, 4),
       (3, '2022-05-05 00:00:00', FALSE, 3, '2022-05-04 00:00:00', 'employee6@employee.com', TRUE, 'Employee6', 'EmployeeLName6', 'Abc1', '0000000012', 'ACTIVE', 3, 4);
