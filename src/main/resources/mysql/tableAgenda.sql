create table agenda (
	 idAgendamento int primary key auto_increment unique,
     nome varchar(90) not null,
     servico varchar (15) not null,
     dataAgendamento date not null, 
     horaAgendamento varchar(10) not null,
	 genero varchar(10),
     email varchar(40) not null,
     telefone varchar(15) not null,
     nomeFunc varchar(45),
     numProc varchar(10) not null,
     cancelado varchar(5),
     dataCancelamento date);
   