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
     
     
     create table disponibilidade(
id_disp int auto_increment unique primary key,
funcionario varchar(120),
data varchar(12),
hora int(4),
categoria int (3),
FOREIGN KEY (categoria)  REFERENCES Categoria(id_categoria),
FOREIGN KEY (hora)  REFERENCES Hora(id_hora));

insert into disponibilidade(funcionario, data, hora, categoria) 
     values
     ( 'Filipe Lucca Sales', '2020-12-10', 78, 61),
     ( 'Filipe Lucca Sales', '2020-12-10', 80, 61),
     ( 'Filipe Lucca Sales', '2020-12-10', 75, 61),
     ( 'Amanda Maitê Giovana Moreira', '2020-12-10', 75, 60),
     ( 'Amanda Maitê Giovana Moreira', '2020-12-11', 72, 60),
     ( 'Vitoria Ester Andreia Porto', '2020-12-10', 100, 60),
     ( 'Vitoria Ester Andreia Porto', '2020-12-14', 71, 60),
     ( 'Eduardo Paulo Silva', '2020-12-12', 75, 62),
     ( 'Eduardo Paulo Silva', '2020-12-12', 71, 62),
     ( 'Eduardo Paulo Silva', '2020-12-10', 110, 62),
     ( 'Eduardo Paulo Silva', '2020-12-10', 81, 62),
     ( 'Sarah Teresinha Caroline da Rosa', '2020-12-10', 71, 63),
     ( 'Sarah Teresinha Caroline da Rosa', '2020-12-16', 74, 63),
     ( 'Sarah Teresinha Caroline da Rosa', '2020-12-16', 81, 63);
     

select * from hora;


select * from disponibilidade;
