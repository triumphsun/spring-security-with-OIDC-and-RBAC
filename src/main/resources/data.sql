INSERT INTO Role (id, name) VALUES
(100, 'ADMIN'),
(101, 'ACCOUNT_MANAGER');


-- bcrypt('password')='$2y$12$Ez/tZ.RYS0LLJwyiWrQKcO84jXo2Ti44bzda3j7xun2eIrY1GtQB6'
INSERT INTO Account (id, email, enabled, password, created_at) VALUES
(100, 'account.mgr@example.com', true, '$2y$12$Ez/tZ.RYS0LLJwyiWrQKcO84jXo2Ti44bzda3j7xun2eIrY1GtQB6', '2021-05-01T00:00:000+08:00');

INSERT INTO Account_Role (account_id, role_id) VALUES
(100, 100),
(100, 101);
