create database if not exists MercadoBD;

use MercadoBD;

create table if not exists Usuarios
(
ID_Usuario int primary key auto_increment,
Nome_Usuario varchar(50) not null,
CPF varchar(15) not null,
Administrador boolean
);
create table if not exists Produtos
(
ID_produto int primary key,
nome_produto varchar(20) not null,
descrição varchar(250) not null,
q_estoque int not null
);