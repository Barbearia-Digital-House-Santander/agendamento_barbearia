CREATE TABLE contato (
  id_comentario int primary key auto_increment,
  nome varchar(30) not null,
  email varchar(70) not null,
  assunto varchar(70),
  mensagem varchar (255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
