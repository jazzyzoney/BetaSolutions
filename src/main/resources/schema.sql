-- Drop tables if they exist (H2 syntax)
DROP TABLE IF EXISTS Profile;

-- Create tables
CREATE TABLE Profile (
                         profile_id INT PRIMARY KEY AUTO_INCREMENT,
                         username VARCHAR(45) NOT NULL,
                         password VARCHAR(20) NOT NULL,
                         profile_email VARCHAR(45) NOT NULL,
                         profile_phone VARCHAR(45) NOT NULL,
                         UNIQUE (username),
                         UNIQUE (profile_email),
                         UNIQUE (profile_phone)
);

