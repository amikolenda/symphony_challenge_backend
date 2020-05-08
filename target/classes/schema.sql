DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS ATHLETE;
CREATE TABLE ROLE (
                      id INT PRIMARY KEY,
                      role_name VARCHAR(100) NOT NULL
);

CREATE TABLE ATHLETE (
                         badge_number VARCHAR(100) PRIMARY KEY NOT NULL,
                         first_name VARCHAR(100),
                         last_name VARCHAR(100),
                         date_of_birth VARCHAR(100) NOT NULL,
                         nationality VARCHAR(10),
                         photo VARCHAR,
                         gender VARCHAR(1),
                         role_id INT,
                         CONSTRAINT fk_athlete_role FOREIGN KEY (role_id)
                             REFERENCES ROLE (id)

);
