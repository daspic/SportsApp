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

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 4', 'user4@yahoo.com', 'User 4', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

INSERT INTO users (contact_info, email, name, password, role) VALUES
('User 5', 'user5@yahoo.com', 'User 5', '$2a$10$0OzJIcKr8ikr41kfEkdDcu5DOMha6wRSly7WgeDpOuv1k.zSaKD3K', 'PLAYER');

-- Table Teams
INSERT INTO teams (name, description, created_at, updated_at) VALUES
('Echipa1', 'cea mai tare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO teams (name, description) VALUES
('Echipa2', 'cea mai slaba');
--

--Table Players
INSERT INTO players (user_id, team_id, player_stats) VALUES (2, 1, 'Stats for Player 1');

INSERT INTO players (user_id, team_id, player_stats) VALUES (3, 1, 'Stats for Player 2');

INSERT INTO players (user_id, team_id, player_stats) VALUES (4, 1, 'Stats for Player 3');

INSERT INTO players (user_id, team_id, player_stats) VALUES (5, 2, 'Stats for Player 4');

INSERT INTO players (user_id, team_id, player_stats) VALUES (6, 2, 'Stats for Player 5');

