CREATE DATABASE  IF NOT EXISTS `spc_brasil` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `spc_brasil`;

CREATE TABLE `paciente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) COLLATE utf8_bin NOT NULL,
  `idade` int(11) NOT NULL,
  `altura` int(11) NOT NULL,
  `peso` int(11) NOT NULL,
  `sexo` char(1) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin



INSERT INTO paciente (nome, idade, altura, peso, sexo) values('Lucas', 23, 196, 80, 'M');
INSERT INTO paciente (nome, idade, altura, peso, sexo) values('Sonia', 48, 161, 58, 'F');
INSERT INTO paciente (nome, idade, altura, peso, sexo) values('João', 35, 179, 72, 'M');
INSERT INTO paciente (nome, idade, altura, peso, sexo) values('Maria', 33, 168, 60, 'F');
INSERT INTO paciente (nome, idade, altura, peso, sexo) values('Barloto', 37, 181, 125, 'M');
INSERT INTO paciente (nome, idade, altura, peso, sexo) values('Susi', 15, 152, 40, 'F');
INSERT INTO paciente (nome, idade, altura, peso, sexo) values('Dean', 34, 178, 75, 'M');
INSERT INTO paciente (nome, idade, altura, peso, sexo) values('Jen', 25, 161, 78, 'F');