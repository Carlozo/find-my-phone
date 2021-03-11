create table usuario (
 id int primary key,
 telefone varchar(11) unique,
 email varchar(25) unique
);