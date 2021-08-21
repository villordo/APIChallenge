create database disney;
use disney;

create table characters(
    id_character int NOT NULL auto_increment PRIMARY KEY,
    name varchar(20) NOT NULL,
    image tinyint(1) NOT NULL,
    age int NOT NULL,
    weigth int NOT NULL,
    history varchar(200) NOT NULL
);

create table genders(
    id_gender int NOT NULL auto_increment PRIMARY KEY,
    name varchar(20) NOT NULL,
    image tinyint(1) NOT NULL
);

create table movies(
    id_movie int NOT NULL auto_increment PRIMARY KEY,
    title varchar(20) NOT NULL,
    image tinyint(1) NOT NULL,
    creation_date datetime NOT NULL,
    qualification int NOT NULL,
    id_gender int NOT NULL,
    CONSTRAINT fk_gender FOREIGN KEY(id_gender) references genders(id_gender)
);

create table characters_x_movies(
    id_character int NOT NULL,
    id_movie int NOT NULL,
    CONSTRAINT fk_character FOREIGN KEY(id_character) references characters(id_character),
    CONSTRAINT fk_movie FOREIGN KEY(id_movie) references movies(id_movie)
);

