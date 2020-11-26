create table disponibilidade(
id_disp int auto_increment unique primary key,
funcionario varchar(60),
data varchar(12),
hora int(4),
categoria int (3),
FOREIGN KEY (categoria)  REFERENCES Categoria(id_categoria),
FOREIGN KEY (hora)  REFERENCES Hora(id_hora));

insert into disponibilidade(funcionario, data, hora, categoria) 
     values( 'Filipe Lucca Sales', '2020-12-10', 78, 61),
      ( 'Filipe Lucca Sales', '2020-12-10', 80, 61),
     ( 'Filipe Lucca Sales', '2020-12-10', 75, 61);
     

select * from disponibilidade;
