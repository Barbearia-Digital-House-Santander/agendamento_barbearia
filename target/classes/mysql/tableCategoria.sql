create table categoria (
	 id_categoria int primary key auto_increment unique,
     nome varchar(15) not null);
     
INSERT INTO categoria
VALUES (60, 'cabelo'),(61, 'barba'), (62, 'pele'), (63, 'unha'); 

select * from categoria;