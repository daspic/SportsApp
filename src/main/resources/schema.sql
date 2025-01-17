CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contact_info VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

-- Create the 'teams' table
CREATE TABLE teams (
    team_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the 'players' table
CREATE TABLE players (
    player_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(50),
    team_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE
);