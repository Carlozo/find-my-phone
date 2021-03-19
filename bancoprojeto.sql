create table usuario (
 id int primary key,
 nome varchar (40),
 email varchar(25) unique,
 telefone varchar(11) unique
);