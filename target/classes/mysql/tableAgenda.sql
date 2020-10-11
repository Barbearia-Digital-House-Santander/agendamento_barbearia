create table agenda (
	 id_agendamento int primary key auto_increment unique,
     cpf varchar(15) not null ,
     nome varchar(90) not null,
     servico varchar (15) not null,
     data_agendamento varchar(12) not null, 
     hora_agendamento varchar(10) not null,
	 genero varchar(10),
     email varchar(40) not null,
     telefone varchar(15) not null,
     nome_func varchar(45),
     chave_de_cancelamento varchar(10) not null unique,
     cancelado varchar(5),
     data_cancelamento varchar(12));