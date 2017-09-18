drop database juliosilveira;
create database juliosilveira;
use juliosilveira;
ALTER DATABASE juliosilveira charset=utf8;
set global max_connections = 550;

CREATE TABLE Usuario (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  nome VARCHAR(100) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (id)
  );

CREATE TABLE Post (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  titulo varchar(200) NOT NULL,
  html varchar(50) NOT NULL,
  data_criacao datetime NOT NULL,
  data_postagem datetime,
  likes MEDIUMINT,
  texto_inicial TEXT,
  imagem TEXT,
  publicado tinyint(1),
  criador MEDIUMINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_USUARIO FOREIGN KEY (criador) REFERENCES Usuario (id));

CREATE TABLE Comentario (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  remetente varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  site varchar(100),
  mensagem text,
  PRIMARY KEY (id)
);

CREATE TABLE PostComentario (
  id_Post MEDIUMINT NOT NULL,
  id_Comentario MEDIUMINT NOT NULL,
  PRIMARY KEY (id_Post,id_Comentario),
  CONSTRAINT FOREIGN KEY (id_Post) REFERENCES Post (id),
  CONSTRAINT FOREIGN KEY (id_Comentario) REFERENCES Comentario (id)
);

CREATE TABLE Tag (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE PostTag (
  id_Post MEDIUMINT NOT NULL,
  id_Tag MEDIUMINT NOT NULL,
  PRIMARY KEY (id_Post,id_Tag),
  CONSTRAINT FOREIGN KEY (id_Post) REFERENCES Post (id),
  CONSTRAINT FOREIGN KEY (id_Tag) REFERENCES Tag (id)
);

CREATE TABLE Depoimento (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  remetente varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  data datetime NOT NULL,
  celular varchar(50) NOT NULL,
  mensagem TEXT,
  foto TEXT,
  cargo varchar(200),
  empresa varchar(200),
  site varchar(200),
  projeto varchar(200),
  aprovado tinyint(1),
  PRIMARY KEY (id)
);

insert into Depoimento values (null, "Júlio Silveira", "julio.silveira.rj@gmail.com", now(), "21 98867-9866", "Primeira Mensagem", "fotosDepoimentos/maxresdefault.jpg", "Site Founder", "Neomind", "http://www.juliosilveiradev.com", "Rio mais Fácil", null);

select * from Depoimento;
  
CREATE TABLE UsuarioRole (
  user_role_id MEDIUMINT NOT NULL AUTO_INCREMENT,
  id_user MEDIUMINT NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_id_user_role (role, id_user),
  KEY fk_username_idx (id_user),
  CONSTRAINT fk_username_role FOREIGN KEY (id_user) REFERENCES Usuario (id));
  
INSERT INTO Usuario()
VALUES (null, 'user', 'user', 'Júlio Silveira', true);

INSERT INTO UsuarioRole (id_user, role)
VALUES (1, 'ROLE_USER');

INSERT INTO UsuarioRole (id_user, role)
VALUES (1, 'ROLE_ADMIN');

select * from Usuario where username = 'user';

select ur.id_user, ur.role from UsuarioRole ur inner join Usuario u on u.id = ur.id_user where u.username = 'user'

select * from Post