create database disney;
use disney;
#drop database disney
SET GLOBAL time_zone = '-03:00';

CREATE TABLE characters  (
    id_character int NOT NULL auto_increment PRIMARY KEY,
    name varchar(20) NOT NULL,
    image tinyint(1) NOT NULL,
    age int NOT NULL,
    weight int NOT NULL,
    history varchar(200) NOT NULL
);

CREATE TABLE genders(
    id_gender int NOT NULL auto_increment PRIMARY KEY,
    name varchar(20) NOT NULL,
    image tinyint(1) NOT NULL
);

CREATE TABLE movies(
    id_movie int NOT NULL auto_increment PRIMARY KEY,
    title varchar(20) NOT NULL,
    image tinyint(1) NOT NULL,
    creation_date datetime NOT NULL,
    qualification int NOT NULL,
    id_gender int NOT NULL,
    CONSTRAINT fk_gender FOREIGN KEY(id_gender) references genders(id_gender)
);

CREATE TABLE characters_x_movies(
    id_character int NOT NULL,
    id_movie int NOT NULL,
    CONSTRAINT fk_character FOREIGN KEY(id_character) references characters(id_character),
    CONSTRAINT fk_movie FOREIGN KEY(id_movie) references movies(id_movie)
);

CREATE TABLE users(
    id_user int NOT NULL auto_increment PRIMARY KEY,
    username varchar(30) NOT NULL,
    pass varchar(150) NOT NULL,
    rol enum('client', 'admin')
);
select * from users;

