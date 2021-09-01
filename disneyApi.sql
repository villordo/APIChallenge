create database disney;
use disney;
#drop database disney
SET GLOBAL time_zone = '-03:00';

CREATE TABLE characters  (
    id_character int NOT NULL auto_increment PRIMARY KEY,
    name varchar(20) NOT NULL,
    image varchar(255) NOT NULL,
    age int NOT NULL,
    weight int NOT NULL,
    history varchar(200) NOT NULL
);

CREATE TABLE genders(
    id_gender int NOT NULL auto_increment PRIMARY KEY,
    name varchar(20) NOT NULL,
    image varchar(255) NOT NULL
);

CREATE TABLE movies(
    id_movie int NOT NULL auto_increment PRIMARY KEY,
    title varchar(20) NOT NULL,
    image varchar(255),
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
    username varchar(30) UNIQUE NOT NULL,
    pass varchar(150) NOT NULL,
    rol enum('client', 'admin')
);

insert into characters (name,image,age,weight,history) values ("Alex Plosivo","",40,75,"Boom!"),
("Cesar Noso","",40,75,"Pica pica!"),("El Georgie","",40,75,"Kpo total!"),("Gustavo Mito","",55,70,"Brggguh!"),
("Marta Tuada","",55,70,"Tinta tinta!"),("Damian Sioso","",35,90,"Tene un cigarro loko?!");
insert into genders (name,image) values ("Horror",""),("Comedia",""),("Misterio","");
insert into movies (title,image,creation_date,qualification,id_gender) values ("La casa del Terror","",NOW(),4,1),
("La novia de mi amigo","",NOW(),4,2),("La casa del lago","",NOW(),4,3),("Dia lluvioso","",NOW(),4,3);
insert into characters_x_movies (id_character,id_movie) values (3,1),(4,1),(5,2),(6,2),(7,2),(8,3),(3,4),(4,4);

select * from genders;
select * from movies;
select * from characters;
select * from characters_x_movies;
select * from users;