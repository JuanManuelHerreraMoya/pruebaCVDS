INSERT into usuario (nombre, correo, contrasena, tipouser, dependencia) values ('Juan', 'juan@gmail.com', '1234', 'Proponente', 'TI');
INSERT into usuario (nombre, correo, contrasena, tipouser, dependencia) values ('Manuel', 'manu@gmail.com', '123456', 'Admin', 'Administrativo');
INSERT into usuario (nombre, correo, contrasena, tipouser, dependencia) values ('Santiago', 'santiago@gmail.com', '123467', 'User', 'Recursos Humanos');
INSERT into usuario (nombre, correo, contrasena, tipouser, dependencia) values ('Adriana', 'adriana@gmail.com', '12345678', 'PMO', 'Unidad de proyectos');

insert into iniciativa (nombre,estado,numerovotos,descripcion,palabrasclave, nombreusuario, correousuario,fecha) values ('preuba', 'Proyecto', 0, 'prueba', 'palabra', 'juan', 'juan@gmail.com', to_date('2020/04/14', 'YYYY/MM/DD'));
insert into iniciativa (nombre,estado,numerovotos,descripcion,palabrasclave, nombreusuario, correousuario,fecha) values ('Iniciativa 1', 'En espera de revisión', 0, 'idea', 'Es una idea', 'santiago', 'santiago@gmail.com', to_date('2020/04/19', 'YYYY/MM/DD'));
update iniciativa set estado='Solucionado', numerovotos=2 where nombre = 'preuba';

insert into comentario (textoComentario, idIniciativa) values ('Es una buena iniciativa', 1);
insert into comentario (textoComentario, idIniciativa) values ('me parece bien', 1);
insert into comentario (textoComentario, idIniciativa) values ('Es una buena idea', 2);
insert into comentario (textoComentario, idIniciativa) values ('Esta bien', 1);
