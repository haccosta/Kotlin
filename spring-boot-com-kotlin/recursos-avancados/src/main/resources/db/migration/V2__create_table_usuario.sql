create table usuario (
  id bigint not null auto_increment,
  nome varchar(100) not null,
  email varchar(100) not null,


  primary key (id)
);

insert into usuario (id, nome, email) values (1, 'Usuario 1', 'teste@teste.com');