create table funcionario (
	 id_func int primary key auto_increment unique,
     cpf varchar(15) not null ,
     nome varchar(90) not null,
     servicos varchar (10) not null,
     data_nasc varchar(12) not null, 
	 genero varchar(10),
     email varchar(40) not null,
     telefone varchar(15) not null,
     endereco varchar(45) not null,
     cep varchar(15) not null ,
     senha varchar(10) not null,
     matricula varchar(5) not null);