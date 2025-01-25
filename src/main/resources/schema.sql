-- Create the sequence for the 'users' table
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;

-- Create the 'users' table
CREATE TABLE users (
    user_id BIGINT DEFAULT nextval('users_seq') PRIMARY KEY,
    contact_info VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

-- Create the sequence for the 'teams' table
CREATE SEQUENCE teams_seq START WITH 1 INCREMENT BY 1;

-- Create the 'teams' table
CREATE TABLE teams (
    team_id BIGINT DEFAULT nextval('teams_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the sequence for the 'players' table
CREATE SEQUENCE players_seq START WITH 1 INCREMENT BY 1;

-- Create the 'players' table
CREATE TABLE players (
    player_id BIGINT DEFAULT nextval('players_seq') PRIMARY KEY,
    user_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    player_stats VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE ON UPDATE CASCADE
);
