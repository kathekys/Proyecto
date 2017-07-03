-- phpMyAdmin SQL Dump
-- version 2.10.1
-- http://www.phpmyadmin.net
-- 
-- Servidor: localhost
-- Tiempo de generación: 03-07-2017 a las 13:27:17
-- Versión del servidor: 5.0.41
-- Versión de PHP: 5.2.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Base de datos: `zapateria`
-- 

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `categorias`
-- 

CREATE TABLE `categorias` (
  `COD_CAT` varchar(10) collate latin1_general_ci NOT NULL,
  `NOM_CAT` varchar(20) collate latin1_general_ci NOT NULL,
  `DESC_CAT` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`COD_CAT`),
  FULLTEXT KEY `COD_CAT` (`COD_CAT`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `categorias`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `clientes`
-- 

CREATE TABLE `clientes` (
  `CED_CLI` varchar(10) collate latin1_general_ci NOT NULL,
  `NOM_CLI` varchar(20) collate latin1_general_ci NOT NULL,
  `APE_CLI` varchar(20) collate latin1_general_ci NOT NULL,
  `TEL_CLI` varchar(10) collate latin1_general_ci NOT NULL,
  `DIR_CLI` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`CED_CLI`),
  KEY `NOM_CLI` (`NOM_CLI`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `clientes`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `compra`
-- 

CREATE TABLE `compra` (
  `NUM_COM` int(11) NOT NULL auto_increment,
  `NOM_PROV` varchar(20) collate latin1_general_ci NOT NULL,
  `FEC_COM` date NOT NULL,
  `REFE_COM` varchar(7) collate latin1_general_ci NOT NULL,
  `COD_PRO_C` varchar(5) collate latin1_general_ci NOT NULL,
  `NOM_PRO_C` varchar(20) collate latin1_general_ci NOT NULL,
  `CANT_PRO_C` int(11) NOT NULL,
  `PRE_UNI_P` float NOT NULL,
  `UTI_PRO_C` float NOT NULL,
  `COST_VENT` float NOT NULL,
  `TOTAL_C` float NOT NULL,
  `VAL_IVA_C` float NOT NULL,
  `SUB_TOTA_C` float NOT NULL,
  `TOT_IVA_C` float NOT NULL,
  `SUM_TOT_C` float NOT NULL,
  PRIMARY KEY  (`NUM_COM`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `compra`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `empleados`
-- 

CREATE TABLE `empleados` (
  `CED_EMP` varchar(10) collate latin1_general_ci NOT NULL,
  `NOM_EMP` varchar(20) collate latin1_general_ci NOT NULL,
  `APE_EMP` varchar(20) collate latin1_general_ci NOT NULL,
  `TEL_EMP` varchar(10) collate latin1_general_ci NOT NULL,
  `DIR_EMP` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`CED_EMP`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `empleados`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `productos`
-- 

CREATE TABLE `productos` (
  `COD_PRO` varchar(5) collate latin1_general_ci NOT NULL,
  `NOM_PRO` varchar(20) collate latin1_general_ci NOT NULL,
  `COL_PRO` varchar(20) collate latin1_general_ci NOT NULL,
  `CANT_PRO` int(20) NOT NULL,
  `PRE_VEN_PRO` float NOT NULL,
  `PRE_COMP_PRO` float NOT NULL,
  `TALL_PRO` int(11) NOT NULL,
  `CAT_PRO` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`COD_PRO`),
  UNIQUE KEY `CAT_PRO` (`CAT_PRO`),
  KEY `NOM_PRO` (`NOM_PRO`),
  KEY `CANT_PRO` (`CANT_PRO`),
  KEY `PRE_VEN_PRO` (`PRE_VEN_PRO`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `productos`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `proveedores`
-- 

CREATE TABLE `proveedores` (
  `COD_PROV` varchar(5) collate latin1_general_ci NOT NULL,
  `NOM_PROV` varchar(20) collate latin1_general_ci NOT NULL,
  `RUC_PROV` varchar(13) collate latin1_general_ci NOT NULL,
  `DIR_PROV` varchar(20) collate latin1_general_ci NOT NULL,
  `TEL_PROV` varchar(10) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`COD_PROV`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `proveedores`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `tallas`
-- 

CREATE TABLE `tallas` (
  `COD_TALL` varchar(5) collate latin1_general_ci NOT NULL,
  `TALLA` int(11) NOT NULL,
  `CANT` int(11) NOT NULL,
  `COD_PRO` varchar(5) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`COD_TALL`),
  KEY `COD_PRO` (`COD_PRO`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `tallas`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `venta`
-- 

CREATE TABLE `venta` (
  `NUM_VEN` int(11) NOT NULL auto_increment,
  `CED_CLI` varchar(10) collate latin1_general_ci NOT NULL,
  `NOM_CLI` varchar(20) collate latin1_general_ci NOT NULL,
  `CED_EMP_V` varchar(10) collate latin1_general_ci NOT NULL,
  `FEC_VEN` date NOT NULL,
  `COD_PRO_V` varchar(5) collate latin1_general_ci NOT NULL,
  `NOM_PRO_V` varchar(20) collate latin1_general_ci NOT NULL,
  `CANT_PRO` int(11) NOT NULL,
  `PRE_UNI` float NOT NULL,
  `DESCU_PRO` float NOT NULL,
  `TOTAL` float NOT NULL,
  `EXIS_PRO` int(11) NOT NULL,
  `VAL_IVA` int(11) NOT NULL,
  `SUM_TOT` float NOT NULL,
  `TOT_IVA` float NOT NULL,
  `SUM_TOTA` float NOT NULL,
  PRIMARY KEY  (`NUM_VEN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `venta`
-- 

