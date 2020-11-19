create table uf (
id_uf int primary key auto_increment unique,
nome varchar(3)
);

INSERT INTO uf (nome)
VALUES ('DF'),('MG'), ('GO'), ('SP'),('RJ'), ('PI'),('BA'); 

select * from uf;