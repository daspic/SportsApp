INSERT INTO users (user_id, contact_info, email, name, password, role) VALUES
(1, 'SQL Contact info', 'adminSQL@example.com', 'SQL Admin User', '$2a$10$qqdTK54UOO87fsvLHSUfcOFCBPzxhG31yBCiJtLQGtaeKy9gQRhbu', 'SQL ADMIN');

INSERT INTO teams (team_id, name, description, created_at, updated_at) VALUES
(1, 'Echipa', 'cea mai tare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO players (player_id, name, position, team_id, created_at) VALUES
(1, 'Georgica', '69', 1, CURRENT_TIMESTAMP);

--'$2a$10$qqdTK54UOO87fsvLHSUfcOFCBPzxhG31yBCiJtLQGtaeKy9gQRhbu'
--
--INSERT INTO user (user_id, contact_info, email, name, password, role) VALUES
--(1, 'SQL Contact info', 'adminSql@example.com', 'Sql Admin User', passwordEncoder.encode("parolaComplicata"), 'ADMIN SQL');
