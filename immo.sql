-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 29 déc. 2020 à 16:55
-- Version du serveur :  5.7.24
-- Version de PHP :  7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `immo`
--

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id_contact` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(250) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `sujet` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_contact`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

DROP TABLE IF EXISTS `departement`;
CREATE TABLE IF NOT EXISTS `departement` (
  `id_departement` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_departement` varchar(100) DEFAULT NULL,
  `id_region` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_departement`),
  KEY `FKa4sgqyqxx4rdms8ahsf6vitm2` (`id_region`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`id_departement`, `nom_departement`, `id_region`) VALUES
(1, 'dakar', 1),
(2, 'guediawaye', 1),
(3, 'pikine', 1),
(4, 'rufisque', 1),
(5, 'thies', 2),
(6, 'Mbour', 2),
(7, 'tivaoune', 2),
(8, 'kaolack', 3),
(9, 'nioro', 3),
(10, 'gingueneo', 3),
(11, 'saint louis', 4),
(12, 'podor', 4),
(13, 'dagana', 4),
(14, 'ziguinchor', 5),
(15, 'bignona', 5),
(16, 'oussouye', 5),
(17, 'tambacounda', 6),
(18, 'bakel', 6),
(19, 'goudiri', 6),
(20, 'koupentoum', 6),
(21, 'diourbel', 7),
(22, 'mbacke', 7),
(23, 'bambey', 7),
(24, 'louga', 8),
(25, 'kebemer', 8),
(26, 'linguere', 8),
(27, 'kolda', 9),
(28, 'medina yoro foulah', 9),
(29, 'velingara', 9),
(30, 'fatick', 10),
(31, 'foundiougne', 10),
(32, 'gossass', 10),
(33, 'matan', 11),
(34, 'ranerou ferlo', 11),
(35, 'kanel', 11),
(36, 'kafrine', 12),
(37, 'birkilane', 12),
(38, 'malem hodar', 12),
(39, 'kounguel', 12),
(40, 'kedougou', 13),
(41, 'salemata', 13),
(42, 'saraya', 13),
(43, 'sedhiou', 14),
(44, 'bounkiling', 14),
(45, 'goudomp', 14);

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
CREATE TABLE IF NOT EXISTS `entreprise` (
  `id_entreprise` bigint(20) NOT NULL AUTO_INCREMENT,
  `email_entreprise` varchar(255) DEFAULT NULL,
  `logo_entreprise` varchar(255) DEFAULT NULL,
  `nom_entreprise` varchar(20) DEFAULT NULL,
  `site_web_entreprise` varchar(255) DEFAULT NULL,
  `telephone_entreprise` varchar(255) DEFAULT NULL,
  `id_ville` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_entreprise`),
  KEY `FKb9mq9vy1q248f9ynwn2eo0gne` (`id_ville`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`id_entreprise`, `email_entreprise`, `logo_entreprise`, `nom_entreprise`, `site_web_entreprise`, `telephone_entreprise`, `id_ville`) VALUES
(1, 'senimmo@gmail.com', 'logo.png', 'Sen Immo', 'www.senimmo.sn', '777661671', 3);

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `id_image` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `nom_image` varchar(255) DEFAULT NULL,
  `id_logement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_image`),
  KEY `FKa6xyt0obrfcu5sygahmj6wiiy` (`id_logement`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `image`
--

INSERT INTO `image` (`id_image`, `image`, `nom_image`, `id_logement`) VALUES
(1, 'image 206.jpg', 'image 206.jpg', 1);

-- --------------------------------------------------------

--
-- Structure de la table `logement`
--

DROP TABLE IF EXISTS `logement`;
CREATE TABLE IF NOT EXISTS `logement` (
  `id_logement` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_enregistrement` datetime DEFAULT NULL,
  `etat_logement` varchar(255) DEFAULT NULL,
  `nombre_chambre_logement` int(11) NOT NULL,
  `nombre_piece_logement` int(11) NOT NULL,
  `nombre_salle_bain_logement` int(11) NOT NULL,
  `photo_logement` varchar(255) DEFAULT NULL,
  `prix_logement` double NOT NULL,
  `proprietaire_logement` varchar(255) DEFAULT NULL,
  `reference_logement` varchar(255) DEFAULT NULL,
  `superficie_logement` varchar(255) DEFAULT NULL,
  `id_entreprise` bigint(20) DEFAULT NULL,
  `id_type_bien` bigint(20) DEFAULT NULL,
  `id_ville` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_logement`),
  KEY `FK3dr59olgjj1q21scek7qhal4u` (`id_entreprise`),
  KEY `FKseb5v62jyufj6alnyyeahhdpf` (`id_type_bien`),
  KEY `FK5dsmdt5cw5ohch1brgqdbagex` (`id_ville`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `logement`
--

INSERT INTO `logement` (`id_logement`, `date_enregistrement`, `etat_logement`, `nombre_chambre_logement`, `nombre_piece_logement`, `nombre_salle_bain_logement`, `photo_logement`, `prix_logement`, `proprietaire_logement`, `reference_logement`, `superficie_logement`, `id_entreprise`, `id_type_bien`, `id_ville`) VALUES
(1, '2020-12-29 00:00:00', 'Location', 3, 5, 1, 'image 206.jpg', 250000, 'Bounda Cissokho Toure', 'REF342', '', 1, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `map`
--

DROP TABLE IF EXISTS `map`;
CREATE TABLE IF NOT EXISTS `map` (
  `id_map` bigint(20) NOT NULL AUTO_INCREMENT,
  `lien_map` varchar(255) DEFAULT NULL,
  `id_logement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_map`),
  KEY `FKr6tfust9pl2rki7rd8v7x7uca` (`id_logement`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `mode_paiement`
--

DROP TABLE IF EXISTS `mode_paiement`;
CREATE TABLE IF NOT EXISTS `mode_paiement` (
  `id_mode_paiement` bigint(20) NOT NULL AUTO_INCREMENT,
  `logo_paiement` varchar(255) DEFAULT NULL,
  `nom_paiement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_mode_paiement`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

DROP TABLE IF EXISTS `operation`;
CREATE TABLE IF NOT EXISTS `operation` (
  `type_operation` varchar(1) NOT NULL,
  `id_operation` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_operation` datetime DEFAULT NULL,
  `id_logement` bigint(20) DEFAULT NULL,
  `code_utilisateur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_operation`),
  KEY `FKksp45fv7edakiic6a9398vq21` (`id_logement`),
  KEY `FKp81ukpa55rvam3n5tev5yymcy` (`code_utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `partenaires`
--

DROP TABLE IF EXISTS `partenaires`;
CREATE TABLE IF NOT EXISTS `partenaires` (
  `id_partenaire` bigint(20) NOT NULL AUTO_INCREMENT,
  `logo_partenaire` varchar(255) DEFAULT NULL,
  `nom_partenaire` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_partenaire`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `partenaires`
--

INSERT INTO `partenaires` (`id_partenaire`, `logo_partenaire`, `nom_partenaire`) VALUES
(1, 'ba3.png', 'BNI'),
(2, 'ba1.png', 'CIB BANK'),
(3, 'ba.png', 'Public BANK'),
(4, 'lg5.png', 'Business Standard');

-- --------------------------------------------------------

--
-- Structure de la table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
CREATE TABLE IF NOT EXISTS `password_reset_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g0guo4k8krgpwuagos61oc06j` (`token`),
  KEY `FK9oxvbsgtqdi3lw9jr1rkxww19` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `paye`
--

DROP TABLE IF EXISTS `paye`;
CREATE TABLE IF NOT EXISTS `paye` (
  `id_paye` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_expiration` datetime DEFAULT NULL,
  `numero_carte` varchar(255) DEFAULT NULL,
  `id_mode_paiement` bigint(20) DEFAULT NULL,
  `id_operation` bigint(20) DEFAULT NULL,
  `code_utilisateur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_paye`),
  KEY `FKqyap2dvc0e3kodyrd5bc5mu11` (`id_mode_paiement`),
  KEY `FK677wcdckxdpivsmdemn0a924l` (`id_operation`),
  KEY `FK1qyqv0yodsymmugk30sodr89f` (`code_utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `region`
--

DROP TABLE IF EXISTS `region`;
CREATE TABLE IF NOT EXISTS `region` (
  `id_region` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_region` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_region`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `region`
--

INSERT INTO `region` (`id_region`, `nom_region`) VALUES
(1, 'dakar'),
(2, 'thies'),
(3, 'kaolack'),
(4, 'saint louis'),
(5, 'ziguinchor'),
(6, 'tambacounda'),
(7, 'diourbel'),
(8, 'louga'),
(9, 'kolda'),
(10, 'fatick'),
(11, 'matam'),
(12, 'kafrine'),
(13, 'kedougou'),
(14, 'sedhiou');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`role`, `description`) VALUES
('ADMIN', 'Responsable de l\'administration du site'),
('USER', 'Utilisateur du système');

-- --------------------------------------------------------

--
-- Structure de la table `temoignage`
--

DROP TABLE IF EXISTS `temoignage`;
CREATE TABLE IF NOT EXISTS `temoignage` (
  `id_temoignage` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `code_utilisateur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_temoignage`),
  KEY `FK9eqt1svgyj6yaudybbe7s0qts` (`code_utilisateur`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `temoignage`
--

INSERT INTO `temoignage` (`id_temoignage`, `message`, `code_utilisateur`) VALUES
(1, 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 'Charlesvictor10'),
(2, 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 'Charlesvictor10'),
(3, 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 'Charlesvictor10');

-- --------------------------------------------------------

--
-- Structure de la table `type_bien`
--

DROP TABLE IF EXISTS `type_bien`;
CREATE TABLE IF NOT EXISTS `type_bien` (
  `id_type_bien` bigint(20) NOT NULL AUTO_INCREMENT,
  `designation` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_type_bien`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_bien`
--

INSERT INTO `type_bien` (`id_type_bien`, `designation`) VALUES
(1, 'Villa');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `username` varchar(50) NOT NULL,
  `active` bit(1) NOT NULL,
  `date_naissance_utilisateur` datetime DEFAULT NULL,
  `email_utilisateur` varchar(255) DEFAULT NULL,
  `nationalite_utilisateur` varchar(255) DEFAULT NULL,
  `nom_utilisateur` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo_utilisateur` varchar(255) DEFAULT NULL,
  `prenom_utilisateur` varchar(50) DEFAULT NULL,
  `profession_utilisateur` varchar(255) DEFAULT NULL,
  `sexe_utilisateur` varchar(255) DEFAULT NULL,
  `telephone_utilisateur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`username`, `active`, `date_naissance_utilisateur`, `email_utilisateur`, `nationalite_utilisateur`, `nom_utilisateur`, `password`, `photo_utilisateur`, `prenom_utilisateur`, `profession_utilisateur`, `sexe_utilisateur`, `telephone_utilisateur`) VALUES
('Charlesvictor10', b'1', '2020-12-29 00:00:00', 'Victorcharleswade@gmail.com', 'Sénégalaise', 'Wade', '$2a$10$gTbniSTCfNMZXWhGCyDzt.hXGwmAhuOzY3y4SqeCp.alL6RsXJEn.', 'IMG_20201226_175444_725.jpg', 'Victor Charles Alfred', 'Informaticien', 'Homme', '781890993');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_role`
--

DROP TABLE IF EXISTS `utilisateur_role`;
CREATE TABLE IF NOT EXISTS `utilisateur_role` (
  `utilisateur_username` varchar(255) NOT NULL,
  `roles_role` varchar(255) NOT NULL,
  KEY `FKdgi097l80w88jfodjxno2igwb` (`roles_role`),
  KEY `FKp4uf7u7jn5yl5m22ickgv24ne` (`utilisateur_username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur_role`
--

INSERT INTO `utilisateur_role` (`utilisateur_username`, `roles_role`) VALUES
('Charlesvictor10', 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `video`
--

DROP TABLE IF EXISTS `video`;
CREATE TABLE IF NOT EXISTS `video` (
  `id_video` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_video` varchar(255) DEFAULT NULL,
  `url_video` varchar(255) DEFAULT NULL,
  `id_logement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_video`),
  KEY `FK7u13nq5cun38xuaqlcy8o8w0e` (`id_logement`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

DROP TABLE IF EXISTS `ville`;
CREATE TABLE IF NOT EXISTS `ville` (
  `id_ville` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_ville` varchar(100) DEFAULT NULL,
  `id_departement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_ville`),
  KEY `FKd8eh7ysuywq3k3uwv8gth2xsd` (`id_departement`)
) ENGINE=MyISAM AUTO_INCREMENT=531 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`id_ville`, `nom_ville`, `id_departement`) VALUES
(1, 'mermoz sacre coeur', 1),
(2, 'ngor', 1),
(3, 'ouakam', 1),
(4, 'yoff', 1),
(5, 'dakar plateau', 1),
(6, 'fann point E amitie', 1),
(7, 'goree', 1),
(8, 'gueule tape fass colobane', 1),
(9, 'medina', 1),
(10, 'grand dakar', 1),
(11, 'biscuterie', 1),
(12, 'hann belair', 1),
(13, 'hlm', 1),
(14, 'sicap liberte', 1),
(15, 'dieupeul derkle', 1),
(16, 'camberene', 1),
(17, 'grand yoff', 1),
(18, 'parcelles assainies', 1),
(19, 'patte doie', 1),
(20, 'golf sud', 2),
(21, 'medina gounass', 2),
(22, 'ndiareme lomamoulaye', 2),
(23, 'sam notaire', 2),
(24, 'wakhinane nimzat', 2),
(25, 'keur massar', 3),
(26, 'malika', 3),
(27, 'yeumbeul nord', 3),
(28, 'yeumbeul sud', 3),
(29, 'pikine dagoudane', 3),
(30, 'dalifort', 3),
(31, 'djida thiaroye kao', 3),
(32, 'guinaw rail nordt', 3),
(33, 'guinaw rail sud', 3),
(34, 'pikine est', 3),
(35, 'pikine ouest', 3),
(36, 'pikine nord', 3),
(37, 'diamaguene sicap mbao', 3),
(38, 'mbao', 3),
(39, 'thiaroy gare', 3),
(40, 'thiaroye sur mer', 3),
(41, 'tivouane diaksao', 3),
(42, 'bargny', 4),
(43, 'diamniadio', 4),
(44, 'sebikhotane', 4),
(45, 'rifisque est', 4),
(46, 'rufisque ouest', 4),
(47, 'rufisque nord', 4),
(48, 'sangakam', 4),
(49, 'kayar', 4),
(50, 'khombaole', 5),
(51, 'pout', 5),
(52, 'djender guegji', 5),
(53, 'fandene', 5),
(54, 'keur moussa', 5),
(55, 'notto', 5),
(56, 'tassette', 5),
(57, 'thienaba', 5),
(58, 'ndieyene sirah', 5),
(59, 'ngoundiane', 5),
(60, 'touba toul', 5),
(61, 'thies nord', 5),
(62, 'thies sud', 5),
(63, 'thies est', 5),
(64, 'thies ouest', 5),
(65, 'mbour', 6),
(66, 'joal fadhiout', 6),
(67, 'ngaparou', 6),
(68, 'nguekhokh', 6),
(69, 'poponguine', 6),
(70, 'saly portudal', 6),
(71, 'somone', 6),
(72, 'thiadiaye', 6),
(73, 'fissel', 6),
(74, 'ndiaganiao', 6),
(75, 'sessene', 6),
(76, 'ngueniene', 6),
(77, 'sandiara', 6),
(78, 'sindia', 6),
(79, 'diass', 6),
(80, 'malikounda', 6),
(81, 'tivaoune', 7),
(82, 'mboro', 7),
(83, 'mekhe', 7),
(84, 'meouane', 7),
(85, 'dorou khodoss', 7),
(86, 'taiba ndiaye', 7),
(87, 'koul', 7),
(88, 'merina dakhar', 7),
(89, 'pekesse', 7),
(90, 'niakhene', 7),
(91, 'mbayene', 7),
(92, 'ngadiouf', 7),
(93, 'thilmakha', 7),
(94, 'pambal', 7),
(95, 'cherif lo', 7),
(96, 'mony rolland', 7),
(97, 'notto gouye diama', 7),
(98, 'pire goureye', 7),
(99, 'kaolack', 8),
(100, 'gandiaye', 8),
(101, 'kahone', 8),
(102, 'ndoffane', 8),
(103, 'koumbale', 8),
(104, 'keur baka', 8),
(105, 'latmingue', 8),
(106, 'thiare', 8),
(107, 'ndiedieng', 8),
(108, 'keur soce', 8),
(109, 'ndiaffate', 8),
(110, 'sibassor', 8),
(111, 'dya', 8),
(112, 'ndiebel', 8),
(113, 'thiomby', 8),
(114, 'nioro du rip', 9),
(115, 'keur madiabel', 9),
(116, 'medina sabakh', 9),
(117, 'kayemor', 9),
(118, 'ngayene', 9),
(119, 'paoskoto', 9),
(120, 'guainte kaye', 9),
(121, 'porokhane', 9),
(123, 'taiba niassene', 9),
(124, 'wack ngouna', 9),
(125, 'keur mabadiakhou', 9),
(126, 'keur ma ndongo', 9),
(127, 'ndrame escale', 9),
(128, 'guinguineo', 10),
(129, 'mbadakhoune', 10),
(130, 'ndiago', 10),
(131, 'ngathie naoude', 10),
(132, 'nguelou', 10),
(133, 'mboss', 10),
(134, 'ngagnik', 10),
(135, 'ourour', 10),
(136, 'saint louis', 11),
(137, 'rao', 11),
(138, 'mpal', 11),
(139, 'fass ngom', 11),
(140, 'gandon', 11),
(141, 'ndiebene gandiole', 11),
(142, 'podor', 12),
(143, 'aere lao', 12),
(144, 'bode lao', 12),
(145, 'demette', 12),
(146, 'galoya toucouleur', 12),
(147, 'golere', 12),
(148, 'guede chantier', 12),
(149, 'mboumba', 12),
(150, 'ndioum', 12),
(151, 'niandane', 12),
(152, 'pete', 12),
(153, 'walalde', 12),
(154, 'cas cas', 12),
(155, 'doumga lao', 12),
(156, 'medina ndiatbe', 12),
(157, 'meri', 12),
(158, 'gamadji sare', 12),
(159, 'guede village', 12),
(160, 'salde', 12),
(161, 'boke dialoube', 12),
(162, 'thile boubacar', 12),
(163, 'fanye', 12),
(164, 'ndiaye pindao', 12),
(165, 'dagana', 13),
(166, 'gae', 13),
(167, 'richard toll', 13),
(168, 'ross bethio', 13),
(169, 'roso senegal', 13),
(170, 'mbane', 13),
(171, 'bokhole', 13),
(172, 'diama', 13),
(173, 'gnith', 13),
(174, 'ronkh', 13),
(175, 'ziguinchor', 14),
(176, 'niaguis', 14),
(177, 'adeane', 14),
(178, 'boutoupa camaracounda', 14),
(179, 'niassia', 14),
(180, 'enampor', 14),
(181, 'bignona', 15),
(182, 'diouloulou', 15),
(183, 'thionkessil', 15),
(184, 'kataba1', 15),
(185, 'djiniaki', 15),
(186, 'kafoutine', 15),
(187, 'sindian', 15),
(188, 'djibidione', 15),
(189, 'oulampane', 15),
(190, 'suel', 15),
(191, 'tendouck', 15),
(192, 'balingore', 15),
(193, 'diegoune', 15),
(194, 'kartlack', 15),
(195, 'mamgagoulack', 15),
(196, 'mlomp', 15),
(197, 'tenghori', 15),
(199, 'coubalan', 15),
(200, 'niamone', 15),
(201, 'ouonck', 15),
(202, 'oussouye', 16),
(203, 'cabrousse', 16),
(204, 'diembering', 16),
(205, 'santhiaba manjaque', 16),
(206, 'loudia ouolof', 16),
(207, 'oukout', 16),
(208, 'tambacounda', 17),
(209, 'koussanar', 17),
(210, 'sinthiou maleme', 17),
(211, 'makakoulibantang', 17),
(212, 'ndoga babacar', 17),
(213, 'niani toucouleur', 17),
(214, 'missirah', 17),
(215, 'dialokoto', 17),
(216, 'neteboulou', 17),
(217, 'bakel', 18),
(218, 'diawara', 18),
(219, 'kidira', 18),
(220, 'bele', 18),
(221, 'sinthiou fissa', 18),
(222, 'keniaba', 18),
(223, 'gathiari', 18),
(224, 'medina foulbe', 18),
(225, 'sadatou', 18),
(226, 'toumboura', 18),
(227, 'moudiry', 18),
(228, 'ballou', 18),
(229, 'gabou', 18),
(230, 'goudiri', 19),
(231, 'kothiary', 19),
(232, 'bala', 19),
(233, 'goumbayel', 19),
(234, 'kaor', 19),
(235, 'boynguel bamba', 19),
(236, 'dougue', 19),
(237, 'koussan', 19),
(238, 'sinthiou mamadou boubou', 19),
(239, 'dianke makha', 19),
(240, 'bani israel', 19),
(241, 'boutoukoufara', 19),
(242, 'komoti', 19),
(243, 'koulor', 19),
(244, 'sinthiou bocar aly', 19),
(245, 'koumpentou', 20),
(246, 'maleme niani', 20),
(247, 'bamba thialene', 20),
(248, 'kahene', 20),
(249, 'mereto', 20),
(250, 'ndam', 20),
(251, 'kouthiaba ouolof', 20),
(252, 'fass koto', 20),
(253, 'payar', 20),
(254, 'diourbel', 21),
(255, 'ndindy', 21),
(256, 'dankh sene', 21),
(257, 'gade escale', 21),
(258, 'ndrame escale', 21),
(259, 'keur ngalou', 21),
(260, 'ndoulo', 21),
(261, 'ngohr', 21),
(262, 'pattar', 21),
(263, 'tocky gare', 21),
(264, 'toure mbonde', 21),
(265, 'mbacke', 22),
(266, 'kael', 22),
(267, 'dendeye gouyegui', 22),
(268, 'darou nahim', 22),
(269, 'darou salam typ', 22),
(270, 'medina', 22),
(271, 'dioumane taiba thiekene', 22),
(272, 'taiba thiekene', 22),
(273, 'touba mboul', 22),
(274, 'ndame', 22),
(275, 'dalla ngabou', 22),
(276, 'missirah', 22),
(277, 'nghaye', 22),
(278, 'touba fall', 22),
(279, 'touba mosquee', 22),
(280, 'taif', 22),
(281, 'sadio', 22),
(282, 'bambey', 23),
(283, 'baba garage', 23),
(284, 'dinguiraye', 23),
(285, 'keur samba kane', 23),
(286, 'lambaye', 23),
(287, 'ngongom', 23),
(288, 'refane', 23),
(289, 'ngoye', 23),
(290, 'dangalma', 23),
(291, 'ndondol', 23),
(292, 'thiakhar', 23),
(293, 'louga', 24),
(294, 'keur momar sarr', 24),
(295, 'gande', 24),
(296, 'nger malal', 24),
(297, 'syer', 24),
(298, 'koky', 24),
(299, 'diagne', 24),
(300, 'pate ouarack', 24),
(301, 'thiamene kayor', 24),
(302, 'mbediene', 24),
(303, 'kele gueye', 24),
(304, 'nguidile', 24),
(305, 'niomre', 24),
(306, 'sakal', 24),
(307, 'leona', 24),
(308, 'gneune sarr', 24),
(309, 'kebemer', 25),
(310, 'gueoul', 25),
(311, 'darou mousty', 25),
(312, 'darou marnane', 25),
(313, 'mbacke kajor', 25),
(314, 'mbadiane', 25),
(315, 'ndoyene', 25),
(316, 'sam yabal', 25),
(317, 'touba merina', 25),
(318, 'ndande', 25),
(319, 'thiep', 25),
(320, 'sagat gueth', 25),
(321, 'kanene ndiob', 25),
(322, 'loro', 25),
(323, 'gourane ouolof', 25),
(324, 'thiolom fall', 25),
(325, 'linguere', 26),
(326, 'dahara', 26),
(327, 'barkedji', 26),
(328, 'gassane', 26),
(329, 'thiargni', 26),
(330, 'thiel', 26),
(331, 'dodji', 26),
(332, 'labgar', 26),
(333, 'ouarkhokh', 26),
(334, 'sagata djolof', 26),
(335, 'boulal', 26),
(336, 'dealy', 26),
(337, 'thiamene passe', 26),
(338, 'yang yang', 26),
(339, 'kambe', 26),
(340, 'mbeuleukhe', 26),
(341, 'mboula', 26),
(342, 'tessekere forage', 26),
(343, 'kolda', 27),
(344, 'dabo', 27),
(345, 'salikegne', 27),
(346, 'sare yoba diega', 27),
(347, 'dioulacolon', 27),
(348, 'guiro yoro bocar', 27),
(349, 'medina el hadji', 27),
(350, 'tankanto escale', 27),
(351, 'mampatim', 27),
(352, 'bagadji', 27),
(353, 'coubankara', 27),
(354, 'dialambere', 27),
(355, 'medina cherif', 27),
(356, 'sare bidji', 9),
(357, 'thietty', 9),
(358, 'medina yoro foulah', 28),
(359, 'pata', 28),
(360, 'fafacourou', 28),
(361, 'badion', 28),
(362, 'ndorna', 28),
(363, 'bignarabe', 28),
(364, 'bourouco', 28),
(365, 'koulinto', 28),
(366, 'niaming', 28),
(367, 'dinguiray', 28),
(368, 'nerewane', 28),
(369, 'velingara', 29),
(370, 'diaobe kabendou', 29),
(371, 'koukane', 29),
(372, 'bonkonto', 29),
(373, 'linkering', 29),
(374, 'medina gounasse', 29),
(375, 'sinthiang koundara', 29),
(376, 'pakour', 29),
(377, 'ouassadou', 29),
(378, 'paroumba', 29),
(379, 'sarre koly salle', 29),
(380, 'kandia', 29),
(381, 'kandiaye', 29),
(382, 'nemataba', 29),
(383, 'fatick', 30),
(384, 'diofior', 30),
(385, 'diakhao', 30),
(386, 'diaoule', 30),
(387, 'mbellacadiao', 30),
(388, 'ndiob', 30),
(389, 'fimela', 30),
(390, 'djilass', 30),
(391, 'loul sessene', 30),
(392, 'palmarin facao', 30),
(393, 'niakhar', 30),
(394, 'ngoyakheme', 30),
(395, 'patar', 30),
(396, 'tattaguine', 30),
(397, 'diarrer', 30),
(398, 'diouroup', 30),
(400, 'foundiougne', 31),
(401, 'karang poste', 31),
(402, 'passy', 31),
(403, 'sokone', 31),
(404, 'soum', 31),
(405, 'djilor', 31),
(406, 'dissong', 31),
(407, 'niodior', 31),
(408, 'bassoul', 31),
(409, 'dionewar', 31),
(410, 'djirnda', 31),
(411, 'toubacouta', 31),
(412, 'keur saloum diané', 31),
(413, 'keur samba gueye', 31),
(414, 'nioro alassane tall', 31),
(415, 'gossass', 32),
(416, 'colobane', 32),
(417, 'mbar', 32),
(418, 'ouadiour', 32),
(419, 'ndiene lagane', 32),
(420, 'patar lia', 32),
(421, 'matam', 33),
(422, 'ourassogui', 33),
(423, 'thilogne', 33),
(424, 'agnam civol', 33),
(425, 'dabia', 33),
(426, 'des agnam', 33),
(427, 'orefonde', 33),
(428, 'ogo', 33),
(429, 'bokidiawe', 33),
(430, 'nabadji civol', 33),
(431, 'ranerou ferlo', 34),
(432, 'velingara ranerou', 34),
(433, 'lougre thioly', 34),
(434, 'oudalaye', 34),
(435, 'kanel', 35),
(436, 'dembankane', 35),
(437, 'hamadi hounare', 35),
(438, 'ouaoude', 35),
(439, 'semme', 35),
(440, 'sinthiou bamambe bandji', 35),
(441, 'orkadiere', 35),
(442, 'dendory', 35),
(443, 'wourou sidy', 35),
(444, 'kaffrine', 36),
(445, 'nganda', 36),
(446, 'gniby', 36),
(447, 'kahi', 36),
(448, 'katakel', 36),
(449, 'diamagadio', 36),
(450, 'diokoul mbelbouck', 36),
(451, 'kathiot', 36),
(452, 'medinatoul salam 2', 36),
(453, 'birkilane', 37),
(454, 'keur mbouki', 37),
(455, 'touba mbella', 37),
(456, 'mabo', 37),
(457, 'ndiognick', 37),
(458, 'malem hodar', 38),
(459, 'darouminam 2', 38),
(460, 'ndiou ngainthe', 38),
(461, 'sagna', 38),
(462, 'kounguel', 39),
(463, 'ida mouride', 39),
(464, 'fass thiekene', 39),
(465, 'saly escale', 39),
(466, 'lour escale', 39),
(467, 'ribo escale', 39),
(468, 'missirah wadene', 39),
(469, 'maka yop', 39),
(470, 'guinte pate', 39),
(471, 'djanke souf', 39),
(472, 'kedougou', 40),
(473, 'bandafassi', 40),
(474, 'dindefelo', 40),
(475, 'ninefecha', 40),
(476, 'toboronkoro', 40),
(477, 'fongolimbi', 40),
(478, 'salemata', 41),
(479, 'dakately', 41),
(480, 'kevoye', 41),
(481, 'dar salam', 41),
(482, 'ethiolo', 41),
(483, 'oubadji', 41),
(484, 'saraya', 42),
(485, 'bembou', 42),
(486, 'medina baffe', 42),
(487, 'sabadola', 42),
(488, 'kkhossanto', 42),
(489, 'missirah sirimana', 42),
(490, 'sedhiou', 43),
(491, 'dianah malary', 43),
(492, 'marsassoum', 43),
(493, 'diende', 43),
(494, 'dianah ba', 43),
(495, 'koussy', 43),
(496, 'oudoucar', 43),
(497, 'sakar', 43),
(498, 'same kanta peulh', 43),
(499, 'djibabouya', 43),
(500, 'bemet bidjini', 43),
(501, 'sansamba', 43),
(502, 'bambaly', 43),
(503, 'djiredji', 43),
(504, 'bounkiling', 44),
(505, 'medina wandifa', 44),
(506, 'bogal', 44),
(507, 'diamakouta', 44),
(508, 'tankon', 44),
(509, 'bona', 44),
(510, 'diacounda', 44),
(511, 'inor', 44),
(512, 'kandion mangana', 44),
(513, 'diaroume', 44),
(514, 'diambaye', 44),
(515, 'faoune', 44),
(516, 'goubomp', 45),
(517, 'diattacounda', 45),
(518, 'samine', 45),
(519, 'tanaff', 45),
(520, 'djibanar', 45),
(521, 'kaour', 45),
(522, 'mangaroungou santo', 45),
(523, 'simbani balante', 45),
(524, 'yarang balante', 45),
(525, 'karantaba', 45),
(526, 'kolibantang', 45),
(527, 'simbani brassou', 45),
(528, 'baghere', 45),
(529, 'dioudoubou', 45),
(530, 'niaga', 45);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
