-- Table Users
INSERT INTO users (contact_info, email, name, password, role) VALUES
('SQL Contact info', 'admin@yahoo.com', 'SQL Admin User', '$2a$10$6XiHAsHg1JHji6JTKn0eMOR3pvpr7PMrEa4vSUgvB448lJejqA/Pm', 'ADMIN');
-- pasword: admin

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 1', 'user1@yahoo.com', 'User 1', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 2', 'user2@yahoo.com', 'User 2', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 3', 'user3@yahoo.com', 'User 3', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

-- Table Teams
INSERT INTO teams (name, description, created_at, updated_at) VALUES
('Echipa', 'cea mai tare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO teams (name, description) VALUES
('Echipa2', 'cea mai slaba');
--
--INSERT INTO players (name, position, team_id, created_at) VALUES
--('Georgica', '69', 1, CURRENT_TIMESTAMP);

--'$2a$10$qqdTK54UOO87fsvLHSUfcOFCBPzxhG31yBCiJtLQGtaeKy9gQRhbu'
--
--INSERT INTO user (user_id, contact_info, email, name, password, role) VALUES
--(1, 'SQL Contact info', 'adminSql@example.com', 'Sql Admin User', passwordEncoder.encode("parolaComplicata"), 'ADMIN SQL');
