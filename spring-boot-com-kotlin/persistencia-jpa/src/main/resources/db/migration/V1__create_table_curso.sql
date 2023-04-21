create table curso (
  id bigint not null auto_increment,
  nome varchar(100) not null,
  categoria varchar(100) not null,
  primary key (id)
);

insert into curso (id, nome, categoria) values (1, 'Curso 1', 'Categoria 1');