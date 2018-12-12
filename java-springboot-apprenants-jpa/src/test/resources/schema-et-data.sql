# DROP SCHEMA IF EXISTS `pratique` ;

CREATE SCHEMA IF NOT EXISTS `pratique` DEFAULT CHARACTER SET utf8 ;
USE `pratique` ;

DROP TABLE IF EXISTS `apprenant`;
CREATE TABLE IF NOT EXISTS `apprenant` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(40) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `apprenant`
--

INSERT INTO `apprenant` (`id`, `prenom`, `nom`, `email`) VALUES
(1, 'Géraldine', 'Test1', 'geraldine@test.fr'),
(2, 'Nicolas', 'Test2', 'nicolas@test.fr'),
(3, 'Pierre', 'Test3', 'pierre@test.fr'),
(4, 'Samuel', 'Test4', 'samuel@test.fr'),
(5, 'Phoneprasong', 'Test5', 'pom@test.fr'),
(6, 'Vincent', 'Test6', 'vincent@test.fr'),
(7, 'Matthieu', 'Test7', 'matthieu@test.fr'),
(8, 'Thomas', 'Test8', 'thomas@test.fr'),
(9, 'Christine', 'Test9', 'christine@test.fr'),
(10, 'Laurent', 'Test10', 'laurent2@test.fr'),
(11, 'David', 'Test11', 'david@test.fr'),
(12, 'Julien', 'Test12', 'julien@test.fr'),
(13, 'Samuel', 'Test13', 'samuel.s@test.fr'),
(14, 'Salvatore', 'Test14', 'salvatore@test.fr'),
(15, 'David', 'Test15', 'david.s@test.fr'),
(16, 'Cédric', 'Test16', 'cedric@test.fr'),
(17, 'philippe', 'Test17', 'philippe@test.fr'),
(18, 'Géronimo', 'Test18', 'gege@bidon.fr'),
(20, 'bidule', 'Test19', 'bidmuche@simplon.co'),
(21, 'machin', 'Test20', 'sdfd@test.fr'),
(23, 'jonnhy', 'Test21', 'jb@moi.com'),
(24, 'Toto', 'Test22', 'toto@test.fr');
