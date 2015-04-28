-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-04-2015 a las 03:48:10
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `jeopardy`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE IF NOT EXISTS `categorias` (
`id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `idMateria` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `idMateria`) VALUES
(4, 'Historia de México', 4),
(5, 'Historia del Mundo', 4),
(10, 'Geometría', 11),
(12, 'Historia de México', 12),
(17, 'Algebra', 11),
(18, 'Trignonometría', 11),
(19, 'Matemáticas Financieras', 11),
(20, 'Métodos Numéricos', 11),
(21, 'Teoría de Números', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juego`
--

CREATE TABLE IF NOT EXISTS `juego` (
`id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `idperfil` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `juego`
--

INSERT INTO `juego` (`id`, `fecha`, `idperfil`) VALUES
(1, '2015-04-27 20:47:19', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juego_jugador`
--

CREATE TABLE IF NOT EXISTS `juego_jugador` (
`id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `puntos` int(11) NOT NULL,
  `idjuego` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `juego_jugador`
--

INSERT INTO `juego_jugador` (`id`, `nombre`, `puntos`, `idjuego`) VALUES
(1, 'Marcelo Cantu', 300, 1),
(2, 'Zyanya Valdes', 600, 1),
(3, 'Eduardo Cristerna', 700, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE IF NOT EXISTS `materias` (
`id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`id`, `nombre`) VALUES
(11, 'Matemáticas'),
(12, 'Historia'),
(16, 'Computación'),
(17, 'Español'),
(19, 'Ética');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE IF NOT EXISTS `perfil` (
`id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`id`, `nombre`) VALUES
(1, 'Grupo MaVi');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil_categoria`
--

CREATE TABLE IF NOT EXISTS `perfil_categoria` (
  `idperfil` int(11) NOT NULL,
  `idcategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `perfil_categoria`
--

INSERT INTO `perfil_categoria` (`idperfil`, `idcategoria`) VALUES
(1, 10),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil_pista`
--

CREATE TABLE IF NOT EXISTS `perfil_pista` (
  `idperfil` int(11) NOT NULL,
  `idpista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `perfil_pista`
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
(1, 34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

CREATE TABLE IF NOT EXISTS `pistas` (
`id` int(11) NOT NULL,
  `pista` text NOT NULL,
  `pregunta` text NOT NULL,
  `puntos` int(11) NOT NULL,
  `idCategoria` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pistas`
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
(34, 'Prueba5', '¿Prueba5?', 500, 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `usuario` varchar(30) NOT NULL,
  `contraseña` varchar(30) NOT NULL,
  `cambioContraseña` tinyint(1) NOT NULL DEFAULT '0',
  `intentosFallidos` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario`, `contraseña`, `cambioContraseña`, `intentosFallidos`) VALUES
('Juan', 'pass', 0, 0),
('Marcelo', 'pass', 0, 0),
('Marco', 'ydobsvwoy', 0, 0),
('Pedro', 'loco', 1, 0),
('prueba', 'loca', 1, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `juego`
--
ALTER TABLE `juego`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `juego_jugador`
--
ALTER TABLE `juego_jugador`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `perfil_categoria`
--
ALTER TABLE `perfil_categoria`
 ADD PRIMARY KEY (`idperfil`,`idcategoria`);

--
-- Indices de la tabla `perfil_pista`
--
ALTER TABLE `perfil_pista`
 ADD PRIMARY KEY (`idperfil`,`idpista`);

--
-- Indices de la tabla `pistas`
--
ALTER TABLE `pistas`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT de la tabla `juego`
--
ALTER TABLE `juego`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `juego_jugador`
--
ALTER TABLE `juego_jugador`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `materias`
--
ALTER TABLE `materias`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `pistas`
--
ALTER TABLE `pistas`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
