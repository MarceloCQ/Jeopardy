-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 28, 2015 at 05:30 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jeopardy`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorias`
--

CREATE TABLE IF NOT EXISTS `categorias` (
`id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `idMateria` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `idMateria`) VALUES
(4, 'Historia de México', 4),
(5, 'Historia del Mundo', 4),
(10, 'Geometría', 11),
(17, 'Algebra', 11),
(18, 'Trignonometría', 11),
(19, 'Matemáticas Financieras', 11),
(20, 'Métodos Numéricos', 11),
(21, 'Teoría de Números', 11),
(22, 'Historia de Italia', 12),
(23, 'Historia de Mexico', 12),
(24, 'Historia China', 12),
(25, 'Mitologia Griega', 12),
(26, 'Mitologia Nórdica', 12),
(27, 'Mitología Egipcia', 12);

-- --------------------------------------------------------

--
-- Table structure for table `juego`
--

CREATE TABLE IF NOT EXISTS `juego` (
`id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `idperfil` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `juego`
--

INSERT INTO `juego` (`id`, `fecha`, `idperfil`) VALUES
(1, '2015-04-27 20:47:19', 1),
(2, '2015-04-27 20:52:50', 2),
(3, '2015-04-27 21:58:06', 3),
(4, '2015-04-27 22:14:51', 3),
(5, '2015-04-27 22:28:07', 4),
(6, '2015-04-27 22:28:31', 4);

-- --------------------------------------------------------

--
-- Table structure for table `juego_jugador`
--

CREATE TABLE IF NOT EXISTS `juego_jugador` (
`id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `puntos` int(11) NOT NULL,
  `idjuego` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `juego_jugador`
--

INSERT INTO `juego_jugador` (`id`, `nombre`, `puntos`, `idjuego`) VALUES
(1, 'Marcelo Cantu', 300, 1),
(2, 'Zyanya Valdes', 600, 1),
(3, 'Eduardo Cristerna', 700, 1),
(4, '1', 200, 2),
(5, '2', 400, 2),
(6, '3', 300, 2),
(7, 'Eduardo', 0, 3),
(8, 'Zyanya', 0, 3),
(9, 'Marcelo', 0, 3),
(10, 'Eduardo', 0, 4),
(11, 'Zyanya', 200, 4),
(12, 'Marcelo', 0, 4),
(13, 'Eduardo', 0, 5),
(14, 'Zyanya', 0, 5),
(15, 'Marcelo', 0, 5),
(16, '1', 0, 6),
(17, '2', 0, 6),
(18, '3', 0, 6);

-- --------------------------------------------------------

--
-- Table structure for table `materias`
--

CREATE TABLE IF NOT EXISTS `materias` (
`id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `materias`
--

INSERT INTO `materias` (`id`, `nombre`) VALUES
(11, 'Matemáticas'),
(12, 'Historia'),
(16, 'Computación'),
(17, 'Español'),
(19, 'Ética'),
(20, 'Idiomas');

-- --------------------------------------------------------

--
-- Table structure for table `perfil`
--

CREATE TABLE IF NOT EXISTS `perfil` (
`id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perfil`
--

INSERT INTO `perfil` (`id`, `nombre`) VALUES
(1, 'Grupo MaVi'),
(2, 'historiadores'),
(3, 'Prueba'),
(4, 'Grupo MaVi 12:00');

-- --------------------------------------------------------

--
-- Table structure for table `perfil_categoria`
--

CREATE TABLE IF NOT EXISTS `perfil_categoria` (
  `idperfil` int(11) NOT NULL,
  `idcategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perfil_categoria`
--

INSERT INTO `perfil_categoria` (`idperfil`, `idcategoria`) VALUES
(1, 10),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(2, 10),
(2, 17),
(2, 18),
(2, 19),
(2, 20),
(2, 21),
(3, 22),
(3, 23),
(3, 24),
(3, 25),
(3, 26),
(3, 27),
(4, 22),
(4, 23),
(4, 24),
(4, 25),
(4, 26),
(4, 27);

-- --------------------------------------------------------

--
-- Table structure for table `perfil_pista`
--

CREATE TABLE IF NOT EXISTS `perfil_pista` (
  `idperfil` int(11) NOT NULL,
  `idpista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perfil_pista`
--

INSERT INTO `perfil_pista` (`idperfil`, `idpista`) VALUES
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(1, 32),
(1, 33),
(1, 34),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 9),
(2, 10),
(2, 11),
(2, 12),
(2, 13),
(2, 14),
(2, 15),
(2, 16),
(2, 17),
(2, 18),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(2, 23),
(2, 24),
(2, 25),
(2, 26),
(2, 27),
(2, 28),
(2, 29),
(2, 30),
(2, 31),
(2, 32),
(2, 33),
(2, 34),
(3, 35),
(3, 37),
(3, 38),
(3, 39),
(3, 40),
(3, 46),
(3, 47),
(3, 48),
(3, 49),
(3, 50),
(3, 51),
(3, 52),
(3, 53),
(3, 54),
(3, 55),
(3, 56),
(3, 57),
(3, 58),
(3, 59),
(3, 60),
(3, 61),
(3, 62),
(3, 63),
(3, 64),
(3, 65),
(3, 67),
(3, 68),
(3, 69),
(3, 70),
(3, 71),
(4, 35),
(4, 37),
(4, 38),
(4, 39),
(4, 40),
(4, 46),
(4, 47),
(4, 48),
(4, 49),
(4, 50),
(4, 51),
(4, 52),
(4, 53),
(4, 54),
(4, 55),
(4, 56),
(4, 57),
(4, 58),
(4, 59),
(4, 60),
(4, 61),
(4, 62),
(4, 63),
(4, 64),
(4, 65),
(4, 67),
(4, 68),
(4, 69),
(4, 70),
(4, 71);

-- --------------------------------------------------------

--
-- Table structure for table `pistas`
--

CREATE TABLE IF NOT EXISTS `pistas` (
`id` int(11) NOT NULL,
  `pista` text NOT NULL,
  `pregunta` text NOT NULL,
  `puntos` int(11) NOT NULL,
  `idCategoria` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pistas`
--

INSERT INTO `pistas` (`id`, `pista`, `pregunta`, `puntos`, `idCategoria`) VALUES
(1, 'Figura que cuenta con tres lados', '¿Qué es un trángulo?', 100, 10),
(2, 'Es una figura de 4 lados.', '¿Qué es un cuadrado?', 200, 10),
(3, 'Es una figura de 5 lados.', '¿Qué es un hexágono?', 500, 10),
(4, 'Es una figura que un número infinito de lados.', '¿Qué es un círculo?', 100, 10),
(5, 'Es una figura con 7 lados.', '¿Qué es un heptágono?', 400, 10),
(7, 'Es una figura que tiene 8 lados.', '¿Qué es un octágono?', 100, 10),
(8, 'Dictador a quien se le acredita el inicio de la segunda guerra mundial.', '¿Quién fue Adolf Hitler?', 200, 13),
(9, 'Es una figura de 10 lados.', '¿Que es un decágono?', 300, 10),
(10, 'Prueba', '¿Prueba?', 100, 17),
(11, 'Prueba2', '¿Prueba2?', 200, 17),
(12, 'Prueba3', '¿Prueba3?', 300, 17),
(13, 'Prueba4', '¿Prueba4?', 400, 17),
(14, 'Prubea5', '¿Prueba5?', 500, 17),
(15, 'Prueba1', '¿Prueba1?', 100, 19),
(16, 'Prueba2', '¿Prueba2?', 200, 19),
(17, 'Prueba3', '¿Prueba3?', 300, 19),
(18, 'Prueba4', '¿Prueba4?', 400, 19),
(19, 'Prueba5', '¿Prueba5?', 500, 19),
(20, 'Prueba1', '¿Prueba1?', 100, 20),
(21, 'Prueba2', '¿Prueba2?', 200, 20),
(22, 'Prueba3', '¿Prueba3?', 300, 20),
(23, 'Prueba4', '¿Prueba4?', 400, 20),
(24, 'Prueba5', '¿Prueba5?', 500, 20),
(25, 'Prueba2', '¿Prueba2?', 200, 21),
(26, 'Prueba1', '¿Prueba1?', 100, 21),
(27, 'Prueba3', '¿Prueba3?', 300, 21),
(28, 'Prueba4', '¿Prueba4?', 400, 21),
(29, 'Prueba5', '¿Prueba5?', 500, 21),
(30, 'Prueba1', '¿Prueba1?', 100, 18),
(31, 'Prueba2', '¿Prueba2?', 200, 18),
(32, 'Prueba3', '¿Prueba3?', 300, 18),
(33, 'Prueba4', '¿Prueba4?', 400, 18),
(34, 'Prueba5', '¿Prueba5?', 500, 18),
(35, 'Monstruo con serpientes en vez de cabello', '¿Quien es Medusa?', 100, 25),
(37, 'Madre Tierra', '¿Quien es Gaia?', 200, 25),
(38, 'Dios del oceano', '¿Quien es Poseidon?', 300, 25),
(39, 'Dios del inframundo', '¿Quien es Hades?', 400, 25),
(40, 'Perro de 3 cabezas que cuida el inframundo', '¿Quien es Cerbero?', 500, 25),
(41, 'Primer presidente de Mexico', '¿Quien fue Guadalupe Victoria?', 100, 4),
(42, 'Fecha en que se celebra el grito de Dolores', '¿Que se celebra 16 de Septiembre, 1810?', 200, 4),
(43, 'Año de la revolucion mexicana', '¿Que es 1910?', 300, 4),
(44, 'Presidente Actual de mexico', '¿Quien es Enrique Peña Nieto?', 400, 4),
(45, 'Candidato a presidencia de Mexico asesinado en 1994', '¿Quien es Luis Donaldo Colosio?', 500, 4),
(46, 'Lugar de nacimiento de movimiento Renacentista', '¿Que es Florencia?', 100, 22),
(47, 'Artista responsable de la capilla Sixtina del Vaticano', '¿Quien es Michelangelo Buonarotti?', 200, 22),
(48, 'Poderosa e Influyente familia entre cuyos miembros había 4 papas', '¿Quienes son los Medici?', 400, 22),
(49, 'Dictador Italiano presidente de la Republica social Italiana durante la segunda guerra mundial', '¿Quien fue Benito Mussolini?', 500, 22),
(50, 'Artista que pintó la Mona Lisa', '¿Quien es Leonardo Da Vinci?', 300, 22),
(51, 'Dios del Trueno', '¿Quien es Thor?', 100, 26),
(52, 'Rey nórdico padre los guerreros legendarios Ivar el Deshuesado y Björn Ragnarsson', 'Quien es Ragnar Lodbrok?', 200, 26),
(53, 'Dios nórdico que sacrificó un ojo a cambio de sabiduría', '¿Quien es Odin?', 300, 26),
(54, 'Dios timador descrito comunmente como "el origen de todo fraude"', '¿Quien es Loki?', 400, 26),
(55, 'El arbol de la vida', '¿Que es Yggdrasil?', 500, 26),
(56, 'Dios del Sol', '¿Quien es Ra?', 100, 27),
(57, 'Dios de los muertos', '¿Quien es Anubis?', 200, 27),
(58, 'Diosa con cabeza de gato', '¿Quien es Bastet?', 300, 27),
(59, 'Dios con cabeza de halcón', '¿Quien es Horus?', 400, 27),
(60, 'Dios que gobernaba el inframundo', '¿Quien es Osiris?', 500, 27),
(61, 'La dinastía más larga de China', '¿Quienes son los Chou?', 100, 24),
(62, 'Conquistó desde China hasta Korea y creó el imperio más grande en la historia', '¿Quien es Genghis Khan?', 200, 24),
(63, 'Revolucionario Comunista quien fundó la República Popular China', '¿Quien es Mao Zedong?', 300, 24),
(64, 'Ciudad más poblada de china', '¿Que es Shangai?', 400, 24),
(65, 'Montaña más alta del mundo con una altura de 8848 metros sobre el nivel del mar', '¿Que es el monte Everest?', 500, 24),
(66, 'Considerado el último gran pintor renacentista y apodado Jacopo Robusti.', '¿Quien es Tintoretto?', 300, 22),
(67, 'Primer presidente de Mexico', '¿Quien fue Guadalupe Victoria?', 100, 23),
(68, 'Fecha en que se celebra el grito de Dolores', '¿Que se celebra 16 de Septiembre, 1810?', 200, 23),
(69, 'Año de la revolucion mexicana', '¿Que es 1910?', 300, 23),
(70, 'Presidente Actual de mexico', '¿Quien es Enrique Peña Nieto?', 400, 23),
(71, 'Candidato a presidencia de Mexico asesinado en 1994', '¿Quien es Luis Donaldo Colosio?', 500, 23);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `usuario` varchar(30) NOT NULL,
  `contraseña` varchar(30) NOT NULL,
  `cambioContraseña` tinyint(1) NOT NULL DEFAULT '0',
  `intentosFallidos` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`usuario`, `contraseña`, `cambioContraseña`, `intentosFallidos`) VALUES
('Juan', 'pass', 0, 0),
('Marcelo', 'pass', 0, 0),
('Marco', 'ydobsvwoy', 0, 0),
('Pedro', 'loco', 1, 0),
('prueba', 'loca', 1, 0),
('zyanya', '12345', 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorias`
--
ALTER TABLE `categorias`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `juego`
--
ALTER TABLE `juego`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `juego_jugador`
--
ALTER TABLE `juego_jugador`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `materias`
--
ALTER TABLE `materias`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perfil`
--
ALTER TABLE `perfil`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perfil_categoria`
--
ALTER TABLE `perfil_categoria`
 ADD PRIMARY KEY (`idperfil`,`idcategoria`);

--
-- Indexes for table `perfil_pista`
--
ALTER TABLE `perfil_pista`
 ADD PRIMARY KEY (`idperfil`,`idpista`);

--
-- Indexes for table `pistas`
--
ALTER TABLE `pistas`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categorias`
--
ALTER TABLE `categorias`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `juego`
--
ALTER TABLE `juego`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `juego_jugador`
--
ALTER TABLE `juego_jugador`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `materias`
--
ALTER TABLE `materias`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `perfil`
--
ALTER TABLE `perfil`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `pistas`
--
ALTER TABLE `pistas`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=72;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
