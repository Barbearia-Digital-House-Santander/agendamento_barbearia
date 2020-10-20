create table cargo (
	 id_cargo int primary key auto_increment unique,
     nome varchar(12) not null);
     
INSERT INTO cargo
VALUES (121, 'gerência'),(122, 'manicure'), (123, 'pedicure'), (124, 'podologo(a)'), (125, 'cabelereiro(a)'),
 (126, 'barbeiro(a)');

select * from cargo; 