create database if not exists MercadoBD;

use MercadoBD;

CREATE USER if not exists 'mercado'@'localhost' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON MercadoBD.* TO 'mercado'@'localhost';

FLUSH PRIVILEGES;

create table if not exists Usuarios
(
ID_usuario int primary key auto_increment,
nome_usuario varchar(50) not null,
CPF varchar(15) not null,
admin boolean
);
create table if not exists Produtos
(
ID_produto int primary key,
nome_produto varchar(20) not null,
categoria varchar(20) not null,
preco varchar(10) not null,
descrição longtext not null,
q_estoque int not null
);

insert into Usuarios (nome_usuario, CPF, admin)
select 'Henrique', '123', true
where not exists(
select 1 from Usuarios where ID_usuario = 1);

select * from Usuarios;
