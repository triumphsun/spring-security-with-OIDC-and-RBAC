INSERT INTO Role (id, name) VALUES
(100, 'ADMIN');

INSERT INTO Account (id, email, enabled, password, created_at) VALUES
(100, 'example.001@gmail.com', true, '$2y$12$Ez/tZ.RYS0LLJwyiWrQKcO84jXo2Ti44bzda3j7xun2eIrY1GtQB6', '2021-05-01T00:00:000+08:00'); //password=password

INSERT INTO Account_Role (user_id, role_id) VALUES
(100, 100);