create table funcionarios (
	 id_func int primary key auto_increment unique,
     cpf varchar(15) not null ,
     nome varchar(90) not null,
     data_nasc varchar(12) not null, 
	 genero varchar(10),
     email varchar(40) not null,
     telefone varchar(15) ,
     nacionalidade varchar(25) default("Brasileira(o)"),
     endereco varchar(45) not null,
     uf varchar(45),
     cep varchar(15)  ,
     categoria int (10) ,
     nivel_func int(2),
     senha varchar(10),
     matricula varchar(11) unique ,
     FOREIGN KEY (categoria)  REFERENCES Categoria(id_categoria),
     FOREIGN KEY (nivel_func) REFERENCES Nivel(id_nivel)
     );
     
     insert into funcionarios values(00001, '163.908.262-06', 'ADM', '2020-10-05', 'Feminino', 'adm@gmail.com', '(61) 99594-5245',
     'Brasileira', 'Brasília', 'DF', '730000-000', 121, 1, 'admin');
     
     select * from funcionarios;