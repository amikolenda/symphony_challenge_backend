DROP TABLE IF EXISTS Athlete;

CREATE TABLE Athlete (
                         id INT AUTO_INCREMENT,
                         first_name VARCHAR(100),
                         last_name VARCHAR(100),
                         date_of_birth VARCHAR(100) NOT NULL,
                         nationality VARCHAR(10),
                         badge_number VARCHAR(100) NOT NULL PRIMARY KEY,
                         photo VARCHAR,
                         gender VARCHAR(1),
                         role VARCHAR(100)

);