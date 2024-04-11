-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 11 avr. 2024 à 20:06
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `aoo`
--

-- --------------------------------------------------------

--
-- Structure de la table `rate`
--

DROP TABLE IF EXISTS `rate`;
CREATE TABLE IF NOT EXISTS `rate` (
  `idrate` int NOT NULL AUTO_INCREMENT,
  `idUser` int DEFAULT NULL,
  `rate` int DEFAULT NULL,
  `commentaire` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rate_strength` int NOT NULL,
  PRIMARY KEY (`idrate`),
  KEY `idUser` (`idUser`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `rate`
--

INSERT INTO `rate` (`idrate`, `idUser`, `rate`, `commentaire`, `name`, `rate_strength`) VALUES
(1, 1, 1, NULL, 'France', 1),
(2, 2, 5, NULL, 'France', 4),
(3, 1, 3, NULL, 'Russia', 1),
(4, 2, 2, NULL, 'United States Minor Outlying Islands', 4),
(5, 1, 5, NULL, 'United States Minor Outlying Islands', 1),
(6, 1, 2, NULL, 'France', 1),
(7, 1, 4, NULL, 'France', 1),
(8, 1, 5, NULL, 'France', 1),
(9, 1, 5, NULL, 'France', 3),
(10, 1, 1, NULL, 'France', 3),
(11, 1, 5, NULL, 'France', 2),
(33, 1, 1, NULL, 'France', 2),
(34, 1, 1, NULL, 'France', 2),
(35, 1, 1, NULL, 'France', 3),
(37, 6, 5, NULL, 'Gibraltar', 1),
(36, 6, 5, NULL, 'France', 1);

-- --------------------------------------------------------

--
-- Structure de la table `report`
--

DROP TABLE IF EXISTS `report`;
CREATE TABLE IF NOT EXISTS `report` (
  `idreport` int NOT NULL AUTO_INCREMENT,
  `idUser` int DEFAULT NULL,
  `idrate` int DEFAULT NULL,
  PRIMARY KEY (`idreport`),
  KEY `idUser` (`idUser`),
  KEY `idrate` (`idrate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `search`
--

DROP TABLE IF EXISTS `search`;
CREATE TABLE IF NOT EXISTS `search` (
  `idsearch` int NOT NULL AUTO_INCREMENT,
  `idUser` int DEFAULT NULL,
  `idrate` int DEFAULT NULL,
  PRIMARY KEY (`idsearch`),
  KEY `idUser` (`idUser`),
  KEY `idrate` (`idrate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `role` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Begginner',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `active`, `role`) VALUES
(1, 'test', 'test', 'test@gmail.com', 1, 'Experienced'),
(2, 'test1', 'test', 'test1@gmail.com', 1, 'GlobeTrotter'),
(3, 'test2', 'test', 'test2@gmail.com', 1, 'Beginner'),
(4, 'test3', 'test', 'test3@gmail.com', 1, 'Beginner'),
(5, 'raymond', 'zqsd', 'raymond@gmail.com', 1, 'Beginner'),
(6, 'raymond2', 'test', 'rerezheng@gmail.com', 1, 'Beginner');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
