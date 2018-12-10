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
(1, 'Géraldine', 'Autrique', 'geraldine.autrique@orange.fr'),
(2, 'Nicolas', 'Filine', 'nicolas.filine@laposte.fr'),
(3, 'Pierre', 'Gorce', 'pierrexgorce@gmail.com'),
(4, 'Samuel', 'Joblon', 'samuel.joblon@gmail.com'),
(5, 'Phoneprasong', 'Khamvongsa', 'pomlao@hotmail.com'),
(6, 'Vincent', 'Lebegue', 'vincent.lebegue@labanquepostale.fr'),
(7, 'Matthieu', 'Londeix', 'matthieu.londeix@laposte.fr'),
(8, 'Thomas', 'Longueville', 'thomas.longueville@laposte.fr'),
(9, 'Christine', 'Métivier', 'christine.pereira@laposte.fr'),
(10, 'Laurent', 'Picard', 'laurent2.picard@laposte.fr'),
(11, 'David', 'Pouline', 'david.pouline@facteo.fr'),
(12, 'Julien', 'Prodhomme', 'prodhomme.julien@gmail.com'),
(13, 'Samuel', 'Sabot', 'samuel.sabot@facteo.fr'),
(14, 'Salvatore', 'Sancesario', 'salvatore.sancesario@facteo.fr'),
(15, 'David', 'Sylvestre', 'david.sylvestre@mfacteur.fr'),
(16, 'Cédric', 'Tressous', 'cedric.tressous@gmail.com'),
(17, 'philippe', 'Bouget', 'philippe.bouget@laposte.net'),
(18, 'Géronimo', 'Bidon', 'gege@bidon.fr'),
(20, 'bidule', 'muche', 'bidmuche@simplon.co'),
(21, 'machin', 'chose', 'sdfd@df.com'),
(23, 'jonnhy', 'begoodii', 'jb@moi.com'),
(24, 'Toto', 'supertoutou', 'toto@laposte.fr');
