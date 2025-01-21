CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
-- Create the 'users' table
CREATE TABLE users (
--    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT DEFAULT nextval('users_seq') PRIMARY KEY,
    contact_info VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE SEQUENCE teams_seq START WITH 1 INCREMENT BY 1;
-- Create the 'teams' table
CREATE TABLE teams (
    team_id BIGINT DEFAULT nextval('teams_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
--
---- Create the 'players' table
--CREATE TABLE players (
--    player_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    position VARCHAR(50),
--    team_id BIGINT,
--    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE
--);