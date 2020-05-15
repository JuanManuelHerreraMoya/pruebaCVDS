create table usuario(
	id serial,
	nombre VARCHAR(120) not NULL,
	correo VARCHAR(120) not NULL,
	contrasena VARCHAR(20) not NULL,
	tipoUser VARCHAR(20) not NULL,
	dependencia VARCHAR(20) not NULL);	

ALTER TABLE usuario 
ADD CONSTRAINT PK_Usuario
PRIMARY KEY (id);

ALTER TABLE usuario 
ADD CONSTRAINT UK_UsuarioCorreo
UNIQUE (correo);

ALTER TABLE usuario 
ADD CONSTRAINT CK_UsuarioTipo
CHECK (tipoUser IN ('Admin', 'User', 'PMO', 'Proponente'));

ALTER TABLE usuario 
ADD CONSTRAINT CK_UsuarioTipoDependencia
CHECK (dependencia in ('Finanzas', 'Administrativo', 'Recursos Humanos', 'TI'));


create table iniciativa(
	id serial,
	nombre VARCHAR(120) not null,
	estado VARCHAR(30) not null,
	numeroVotos int not null,
	descripcion VARCHAR(300) not null,
	palabrasClave VARCHAR(180) not null,
	nombreUsuario VARCHAR(120) not null,
	correoUsuario VARCHAR(120) not null,
	fecha date not null);

ALTER TABLE iniciativa 
ADD CONSTRAINT PK_iniciativa
PRIMARY KEY (id);

ALTER TABLE iniciativa 
ADD CONSTRAINT UK_iniciativaNombre
UNIQUE (nombre);

ALTER TABLE iniciativa 
ADD CONSTRAINT CK_iniciativaEstado
CHECK (estado IN ('En espera de revisión', 'En revisión', 'Proyecto', 'Solucionado'));

CREATE TABLE comentario(
	id serial,
	textoComentario VARCHAR(200) NOT NULL,
	idIniciativa int NOT NULL);

ALTER TABLE comentario
ADD CONSTRAINT PK_Comentario
PRIMARY KEY (id);
