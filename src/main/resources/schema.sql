DROP TABLE IF EXISTS TBL_USER;

CREATE TABLE TBL_USER (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    email VARCHAR(250) DEFAULT NULL
);