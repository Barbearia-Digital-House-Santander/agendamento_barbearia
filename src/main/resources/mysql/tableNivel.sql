create table nivel (
	 id_nivel int primary key auto_increment unique,
     descricao varchar(15) not null);
     
INSERT INTO nivel
VALUES (1, 'gerencia'), (2, 'comum'), (3, 'cliente'); 

select * from nivel;