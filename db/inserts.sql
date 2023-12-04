
insert into usuario (email, nome_de_usuario, apelido, rollpoints, senha, ativo, email_valido, imagem_perfil, imagem_capa)
values ('admin@gmail.com.br', 'admin', 'Admin', 0,
'$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true, true, "perfil.jpg", "");

insert into usuario (email, nome_de_usuario, apelido, rollpoints, senha, ativo, email_valido, imagem_perfil, imagem_capa)
values ('ruann@gmail.com.br', 'ruann', 'Ruann', 0,
'$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true, true, "perfil.jpg", "");

insert into usuario (email, nome_de_usuario, apelido, rollpoints, senha, ativo, email_valido, imagem_perfil, imagem_capa)
values ('marcelle@gmail.com.br', 'marcelle', 'Marcelle', 0,
'$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true, true, "perfil.jpg", "");

INSERT INTO permissao (funcao, usuario_id) VALUES ('ADMIN', 1);
INSERT INTO permissao (funcao, usuario_id) VALUES ('USUARIO', 1);

INSERT INTO categoria (nome) VALUES ('Personagem');
INSERT INTO categoria (nome) VALUES ('Arte');
INSERT INTO categoria (nome) VALUES ('História');
INSERT INTO categoria (nome) VALUES ('Ficha');
INSERT INTO categoria (nome) VALUES ('Monstro');
INSERT INTO categoria (nome) VALUES ('Suplemento');
INSERT INTO categoria (nome) VALUES ('Outro');

INSERT INTO cosmetico (nome, tipo, custo) VALUES ("RollShare", "FUNDO", 0);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("RollShare", "INSIGNIA", 0);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("Ordem Paranormal", "FUNDO", 100);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("ungeons & Dragons", "FUNDO", 100);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("Tormenta20", "FUNDO", 100);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("Simbolo de Morte", "INSIGNIA", 50);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("Simbolo de Sangue", "INSIGNIA", 50);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("Simbolo de Conhecimento", "INSIGNIA", 50);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("Simbolo de Energia", "INSIGNIA", 50);
INSERT INTO cosmetico (nome, tipo, custo) VALUES ("Simbolo de Medo", "INSIGNIA", 50);

-- Cosméticos Iniciais de ADM --
INSERT INTO usuario_cosmetico (usuario_id, cosmetico_id) VALUES (1,1);
INSERT INTO usuario_cosmetico (usuario_id, cosmetico_id) VALUES (1,2);
-- Cosméticos Iniciais de Ruann --
INSERT INTO usuario_cosmetico (usuario_id, cosmetico_id) VALUES (2,1);
INSERT INTO usuario_cosmetico (usuario_id, cosmetico_id) VALUES (2,2);
-- Cosméticos Iniciais de Marcelle --
INSERT INTO usuario_cosmetico (usuario_id, cosmetico_id) VALUES (3,1);
INSERT INTO usuario_cosmetico (usuario_id, cosmetico_id) VALUES (3,2);