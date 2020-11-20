create table funcionarios (
	 id_func int primary key auto_increment unique,
     cpf varchar(15) not null ,
     nome varchar(90) not null,
     data_nasc varchar(12) , 
	 genero varchar(10),
     email varchar(40),
     telefone varchar(15) ,
     nacionalidade varchar(25) default("Brasileira(o)"),
     endereco varchar(45),
     uf varchar(45),
     cep varchar(15)  ,
     categoria int (10) ,
     nivel_func int(2),
     senha varchar(10),
     matricula varchar(11) unique ,
     FOREIGN KEY (categoria)  REFERENCES Categoria(id_categoria),
     FOREIGN KEY (nivel_func) REFERENCES Nivel(id_nivel)
     );
     
     insert into funcionarios(cpf, nome, email, matricula, senha, nivel_func) 
     values( '163.908.262-06', 'ADM', 'adm@gmail.com', '2020DHBR', '12345', 1);
     
     select * from funcionarios;