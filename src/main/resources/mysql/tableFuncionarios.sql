create table funcionarios (
	 id_func int primary key auto_increment unique,
     cpf varchar(15) ,
     nome varchar(90) ,
     data_nasc varchar(12) , 
	 genero varchar(10),
     email varchar(90),
     telefone varchar(15) ,
     nacionalidade varchar(25) default("Brasileira(o)"),
     endereco varchar(45),
     uf varchar(45),
     cep varchar(15)  ,
     categoria int (10) ,
     nivel_func int(3),
     senha varchar(110),
     matricula varchar(11) unique ,
     FOREIGN KEY (categoria)  REFERENCES Categoria(id_categoria),
     FOREIGN KEY (nivel_func) REFERENCES Nivel(id_nivel)
     );
     
     insert into funcionarios(cpf, nome, email, matricula, senha, nivel_func) 
     values( '163.908.262-06', 'ADM', 'adm@gmail.com', '2020DHBR', '$2a$12$W3/XT68P1GnBUxtyi9/Nre52VtRFws51LDvNVpvD/Z.v4Pxg/6.r.', 1);
     
     insert into funcionarios 
     values( 3, '954.539.360-29', 'Leandro Diogo Gomes', '1987-12-05', 'masculino', 'barber.santander.digitahouse@gmail.com', '(61) 9855-5454', 'Brasileira(o)', 'Rua Maceió', 1, '59086-280', 61, 1, '$2a$12$ntLY7obtmQOK0k3y6DlrauCXVlxpU5DvfIaOQS4pJWcqVAhNJMq.e', '2020DHBR'),
     (2, '159.308.683-01', 'Marcelo José Ramos', '1987-08-05', 'masculino', 'barber.santander.digitahouse@gmail.com', '(84) 2680-7192', 'Brasileira(o)', 'Rua Maceió', 7, '59086-280', 60, 2, '$2a$12$.1bxRyqXkGNkOucxSNCbFuztA0KxFgSx.ZAXKAFE6KwSh.qzFzGmO', '0VCCA4CKYE'),
     ( 10, '742.543.448-21', 'Hugo Rafael Benjamin Moreira', '1982-05-04', 'masculino', 'barber.santander.digitahouse@gmail.com', '(44) 4444-4444', 'Brasileira(o)', 'Rua Cerro Largo', 1, '51280-060', 62, 2, '$2a$12$OjTtrxpZeRxPiA9VEozbseHxvTbEz1rugsFcEuGRXFY9H5nBkPvt2', 'WU0A0ZH14N');
     
     select * from funcionarios;
     select * from funcionarios;