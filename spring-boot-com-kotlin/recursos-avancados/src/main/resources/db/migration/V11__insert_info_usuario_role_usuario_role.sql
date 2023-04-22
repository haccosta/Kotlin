insert into usuario (id, nome, email, password) values (2, 'admin', 'admin@admin.com', '$2a$12$puM/XI.QC1wIDvMaNeZ/b.fkwNnUp9OKZ.q/OrE.tMOOplYyjpv5m');
insert into role (id, nome) values (2, 'ADMIN');
insert into usuario_role (id,usuario_id,role_id) values (2,2,2);