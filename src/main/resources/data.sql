INSERT INTO Role (id, name) VALUES
(100, 'ADMIN');

INSERT INTO Account (id, email, enabled, password, created_at) VALUES
(100, 'example.001@gmail.com', true, '$2y$12$t1ny1JSnQG4SHRXVD0bKHOQObmMHOF4lMiVH1.Wn2oEInxagqNXuC', '2021-05-01T00:00:000+08:00');

INSERT INTO Account_Role (user_id, role_id) VALUES
(100, 100);