DROP TABLE IF EXISTS ATHLETE;

CREATE TABLE ATHLETE (
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         badge_number VARCHAR(100) NOT NULL UNIQUE,
                         first_name VARCHAR(100),
                         last_name VARCHAR(100),
                         date_of_birth VARCHAR(100) NOT NULL,
                         nationality VARCHAR(10),
                         photo VARCHAR,
                         gender VARCHAR(1),
                         role_name VARCHAR(100)

);