
CREATE TABLE servicos (
   id_servico int PRIMARY KEY AUTO_INCREMENT NOT NULL,
   nome varchar(50),
   descricao varchar(100) ,
   valor varchar(10),
   categoria int(3) ,
   FOREIGN KEY (categoria) REFERENCES categoria(id_categoria)
);
INSERT INTO servicos
VALUES (101, 'corte simples', 'aparar cabelo ou corte social', 'R$15,00',60),(102, 'corte estiloso', 'corte com design', 'R$25,00',60), 
(103, 'barba simples', 'aparar barba ou barba social', 'R$10,00',61), (104, 'barba estilosa', 'barba com design', 'R$17,50',61),
(105, 'limpeza de pele', 'esfoliação no rosto', 'R$15,00',62), (106, 'Manicure ou Pedicure', 'limpeza e pintura de unha',  'R$25,00',63), (107, 'podologia', 'podologia',  'R$65,00',63); 

select * from servicos;

