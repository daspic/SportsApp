-- Table Users
INSERT INTO users (contact_info, email, name, password, role) VALUES
('SQL Contact info', 'admin@yahoo.com', 'SQL Admin User', '$2a$10$6XiHAsHg1JHji6JTKn0eMOR3pvpr7PMrEa4vSUgvB448lJejqA/Pm', 'ADMIN');
-- pasword: admin

-- for all next users the password is "user"
INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 1', 'oana@yahoo.com', 'Oana', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 2', 'mara@yahoo.com', 'Mara', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 3', 'ani@yahoo.com', 'Ani', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 4', 'vasile@yahoo.com', 'Vasile', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 5', 'magda@yahoo.com', 'Magda', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 6', 'mandelu@yahoo.com', 'Mandelu', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

-- Table Teams
INSERT INTO teams (name, description, created_at, updated_at) VALUES
('Steaua', 'cea mai tare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO teams (name, description) VALUES
('Dinamo', 'cea mai slaba');
--

--Table Players
INSERT INTO players (user_id, team_id, player_stats) VALUES (2, 1, 'Stats for Player 2');

INSERT INTO players (user_id, team_id, player_stats) VALUES (3, 1, 'Stats for Player 3');

INSERT INTO players (user_id, team_id, player_stats) VALUES (4, 1, 'Stats for Player 4');

INSERT INTO players (user_id, team_id, player_stats) VALUES (5, 2, 'Stats for Player 5');

INSERT INTO players (user_id, team_id, player_stats) VALUES (6, 2, 'Stats for Player 6');

INSERT INTO players (user_id, team_id, player_stats) VALUES (7, 2, 'Stats for Player 7');

