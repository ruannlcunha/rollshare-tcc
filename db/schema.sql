DROP TABLE IF EXISTS permissao CASCADE;
DROP TABLE IF EXISTS perfil_favorito CASCADE;
DROP TABLE IF EXISTS avaliacao CASCADE;
DROP TABLE IF EXISTS usuario_categoria CASCADE;
DROP TABLE IF EXISTS usuario_cosmetico CASCADE;
DROP TABLE IF EXISTS denuncia CASCADE;
DROP TABLE IF EXISTS cosmetico CASCADE;
DROP TABLE IF EXISTS conteudo_categoria CASCADE;
DROP TABLE IF EXISTS categoria CASCADE;
DROP TABLE IF EXISTS conteudo_imagem CASCADE;
DROP TABLE IF EXISTS conteudo CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;

CREATE TABLE usuario (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nome_de_usuario VARCHAR(255) NOT NULL UNIQUE,
	apelido VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	senha VARCHAR(128) NOT NULL,
	imagem_perfil VARCHAR(5000) NOT NULL DEFAULT(""),
    imagem_capa VARCHAR(5000) NOT NULL DEFAULT(""),
    token_seguranca VARCHAR(500),
    rollpoints INT NOT NULL DEFAULT(0),
    email_valido BOOLEAN NOT NULL DEFAULT(false),
	ativo BOOLEAN NOT NULL DEFAULT(true)
);

CREATE TABLE permissao (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	funcao VARCHAR(100) NOT NULL,
	usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_permissao_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
    CONSTRAINT uk_permissao UNIQUE(funcao,usuario_id)
);

CREATE TABLE perfil_favorito (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	usuario_id BIGINT NOT NULL,
	favorito_id BIGINT NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT(true),
    CONSTRAINT uk_favorito UNIQUE (usuario_id, favorito_id),
    CONSTRAINT fk_favorito_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT fk_perfil_favorito FOREIGN KEY (favorito_id) REFERENCES usuario(id)
);

CREATE TABLE conteudo (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	descricao VARCHAR(255) NOT NULL,
	data_inicio TIMESTAMP NOT NULL,
    foi_alterado BOOLEAN NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT(true),
	usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_conteudo_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE conteudo_imagem (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	data_uri VARCHAR(5000),
	conteudo_id BIGINT NOT NULL,
    CONSTRAINT fk_conteudo_imagem FOREIGN KEY (conteudo_id) REFERENCES conteudo(id)
);

CREATE TABLE categoria (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	nome VARCHAR(200) NOT NULL
);

CREATE TABLE conteudo_categoria (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	conteudo_id BIGINT NOT NULL,
	categoria_id BIGINT NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT(true),
    CONSTRAINT uk_categoria UNIQUE (conteudo_id, categoria_id),
    CONSTRAINT fk_conteudo_categoria_usuario FOREIGN KEY (conteudo_id) REFERENCES conteudo(id),
	CONSTRAINT fk_conteudo_categoria_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE avaliacao (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	descricao VARCHAR(255) NOT NULL,
    nota INT NOT NULL,
	data_inicio TIMESTAMP NOT NULL,
    foi_alterado BOOLEAN NOT NULL DEFAULT(false),
	ativo BOOLEAN NOT NULL DEFAULT(true),
	conteudo_id BIGINT NOT NULL,
	usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_avaliacao_conteudo FOREIGN KEY (conteudo_id) REFERENCES conteudo(id),
    CONSTRAINT fk_avaliacao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


CREATE TABLE usuario_categoria (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	usuario_id BIGINT NOT NULL,
	categoria_id BIGINT NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT(true),
    CONSTRAINT uk_categoria UNIQUE (usuario_id, categoria_id),
    CONSTRAINT fk_usuario_categoria_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT fk_usuario_categoria_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);


CREATE TABLE cosmetico (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	nome VARCHAR(200) NOT NULL,
    tipo VARCHAR(200) NOT NULL,
    custo INT NOT NULL
);

CREATE TABLE usuario_cosmetico (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	usuario_id BIGINT NOT NULL,
	cosmetico_id BIGINT NOT NULL,
    em_uso BOOLEAN NOT NULL DEFAULT(true),
    CONSTRAINT uk_cosmetico UNIQUE (usuario_id, cosmetico_id),
    CONSTRAINT fk_usuario_cosmetico_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT fk_usuario_cosmetico_cosmetico FOREIGN KEY (cosmetico_id) REFERENCES cosmetico(id)
);

CREATE TABLE denuncia (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	motivo VARCHAR(800),
    usuario_id BIGINT NOT NULL,
    conteudo_id BIGINT NOT NULL,
	data_inicio TIMESTAMP NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT(true),
    CONSTRAINT fk_denuncia_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT fk_denuncia_cosmetico FOREIGN KEY (conteudo_id) REFERENCES conteudo(id)
);
