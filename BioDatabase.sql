DROP DATABASE IF EXISTS cinema;
CREATE DATABASE cinema;
USE cinema;

CREATE TABLE cinema(
ID INT NOT NULL AUTO_INCREMENT,
name VarChar(255) NOT NULL,
PRIMARY KEY(ID)
);

CREATE TABLE hall(
ID INT NOT NULL AUTO_INCREMENT,
rows_ INT NOT NULL,
coloumns INT NOT NULL,
cinema_ID INT NOT NULL,
movie_name VarChar(63) NOT NULL,
CONSTRAINT fk_cinema_cinema FOREIGN KEY (cinema_ID) REFERENCES cinema (ID) ON UPDATE CASCADE,
PRIMARY KEY(ID)
);

CREATE TABLE seat(
rows_number INT NOT NULL,
coloumns_number INT NOT NULL,
reservation_ID INT NOT NULL,
hall_ID INT NOT NULL,
CONSTRAINT fk_cinema_hall FOREIGN KEY (hall_ID) REFERENCES hall (ID) ON UPDATE CASCADE,
PRIMARY KEY(rows_number, coloumns_number, hall_ID)
);

CREATE TABLE reservation(
ID INT NOT NULL AUTO_INCREMENT,
phone_number VarChar(15) NOT NULL,
password VarChar(31) NOT NULL,
PRIMARY KEY(ID)
);