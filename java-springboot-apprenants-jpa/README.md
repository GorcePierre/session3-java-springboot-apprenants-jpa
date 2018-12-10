# java-springboot-apprenant avec JPA-Hibernate-MySQL 

Ce projet est une application SpringBoot basique permettant de gérer une base de données d'apprenants avec les opérations CRUD standards. 
Il utilise la base de données de test **apprenants.sql que vous devez installer dans votre base Mysql **pratique**.

**Côté front** : un client html javascript natif utilisant les bibliothèques jquery & bootstrap et l'objet **DataTable**.

**Côté back**  : une application Java SpringBoot qui implémente les principes du MVC avec les classes suivantes.

- ApprenantApplication.java (pour lancer l'application SpringBoot)
- AppenantController.java (permet de faire le lien entre le modèle et la vue) => Controleur
- ApprenantRepository.java (Classe qui hérite de JpaRepository qui permet un CRUD suffisant)
- Apprenant.java (classe **@Entity** qui correspond à la table **apprenant** dans MySQL) => Modèle

- index.html (page incluant un tableau et un formulaire avec des boutons) => Vue
- apprenant.js (fonctionnalités AJAX pour faire le lien avec le contrôleur Java)

- applications.properties qui contient les paramètres pour Hibernate et MySQL

L'objectif de ce tutoriel est de fournir un projet permettant de travailler les aspects suivants :
 
 * Analyser un client REST construit avec SpringBoot (ce que vous avez déjà fait la semaine dernière)
 * Comprendre le fonctionnement d'une API REST
 * Voir comment un client web utilise une API REST avec une page HTML et avec du code javascript.

# Prérequis

Pour faire fonctionner ce projet, l'installation des composants suivants est conseillé :

 * Eclipse avec STS
 * Mysql
 * STS - Spring Tool Suite (plugin Eclipse)
 * Lombok

Pour créer ce projet dans Eclipse, faire un import du projet Maven. 

# Eléments de configuration à étudier

### pom.xml
Voici quelques dépendances ajoutées :
 * le système de gestion de base de données JDBC (spring-boot-starter-jdbc)
 * le connecteur mysql associé à JDBC (mysql-connector-java)
 * la bibliothèque javascript JQuery
 * le framework UI bootstrap
 * ...

```xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>2.2.4</version>
		</dependency>

		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>3.3.7</version>
		</dependency>
		<dependency>
			<groupId>org.hibernate.javax.persistence</groupId>
			<artifactId>hibernate-jpa-2.1-api</artifactId>
			<version>1.0.0.Final</version>
		</dependency>
		<dependency>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-ejb-plugin</artifactId>
			<version>2.3</version>
			<type>maven-plugin</type>
		</dependency>
		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-commons</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
	</dependencies>
```

**Remarque :** Il est possible de forcer l'utilisation d'une version d'une des dépendances en ajoutant une balise **<version>** contenant l'identifiant de la version à utiliser. Dans le cas où ce n'est pas spécifié, c'est SpringBoot qui détermine quel est la meilleure version à utiliser.

### application.properties
Vous trouverez dans ce fichier les informations de configuration pour la base de données et pour le système de log. 

```properties
# ===============================
# base de données MySQL
# ===============================
spring.datasource.url=jdbc:mysql://localhost:3306/pratique?useSSL=false
spring.datasource.username=test
spring.datasource.password=test
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
# log
logging.level.root=INFO
logging.file=d:/data/log-java-springboot-apprenant-jpa.log
logging.level.org.springframework.jdbc.core.JdbcTemplate=debug
# ===============================
# JPA / HIBERNATE
# ===============================
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

**Remarque :** Si l'application ne fonctionne pas, c'est peut-être dû à vos paramètres mysql ?! C'est ici qu'il vous faudra travailler pour corriger le problème.

# Code source

Notre code source est organisé selon les principes du MVC (Model View Controller) légrement simplifié :

 * co.simplon.springboot.apprenant.model : package contenant les éléments du modèle
 * co.simplon.springboot.apprenant.controller : package contenant les contrôleurs de l'application
 * co.simplon.springboot.apprenant.repository : package contenant les classes dao (gestion de la base de données)
   
 Habituellement, on ajoute une classe service qui est contient les classes métiers (business) permettant d'établir le lien entre le modèle et le contrôleur.
 
Les éléments relatifs à la Vue sont présents dans le répertoire src/main/resources/static (partie front).
 
Cette structure n'est pas obligatoire mais c'est une bonne pratique de s'en inspirer. Elle permet d'organiser la structure du projet et de regrouper les classes "intelligemment".

## Le modèle

#### Apprenant.java
Il s'agit de la classe de notre modèle de données. Sa structure correspond à la structure de la table associée dans la base de données. On peut la considérer comme un Bean java standard. Il est donc conseillé qu'elle contienne :

 * un constructeur par défaut
 * des Getters et Setters pour tous les attributs de la classe
 * grâce à Lombok on peut se contenter de ne mettre que les attributs

## La couche DAO avec la classe ApprenantRepository

La couche DAO est la couche qui gère la persistance des données. Cette couche apporte les méthodes CRUD classiques pour les classes du modèle associées.
  
#### ApprenantDAO.java (absente dans cette version JPA-Hibernate, mais présente dans la version JDBC)
Il s'agit d'une interface java classique qui contient les méthodes pour créer, modifier, supprimer et retrouver des données de type **Apprenant** dans la base de données.  

#### ApprenantRepository.java
ApprenantRepository est la classe d'implémentation associée au modèle **Apprenant.java**. Elle porte normalement le code capable de produire et exécuter les requêtes SQL nécessaires à la persistance des données de type **Apprenant**. Elle est surmontée de l'annotation **@Repository** permettant au système de résolution des dépendances.

Vous voyez pourtant qu'aucune méthode

```java
@RepositoryRestResource
public interface ApprenantRepository  extends JpaRepository<Apprenant, Integer> {

	// rien à ajouter car toutes les fonctionnalités CRUD sont existantes dans la 
	// classe JpaRepository... c'est cool !) 
}
```

## Le couche service (pas ici, il n'y a qu ele repository)
C'est la couche métier de l'application.  
C'est ici qu'est décrit le comportement des classes (diagramme des séquences), les transactions, les relations entre les classes.  
Le service sert de transition, est appelé par le contrôleur et agit sur le modèle.
Bien qu'il fasse partie du modèle, il est placé dans le package service.

**Dans une application aussi simple, une classe ApprenantService supplémentaire n'a que peu d'intérêt**.
Service est une classe "Passe Plat" qui ne fait que relayer les méthodes de la classe **ApprenantRepository**. Pour déclarer son existence à Spring, on la précède de l'annotation @Service

## Le contrôleur

#### ApprenantController.java
Il s'agit du contrôleur de notre application pour le modèle de donnée Apprenant. C'est cette classe qui va gérer l'API REST de notre application. Les contrôleurs au sens "Spring" sont repérés par une annotation spéciale

```java
@RestController
@RequestMapping("/api")
public class ApprenantController {
```

L'annotation **@RestController** est une version spécialisée de l'annotation **@Controller**. Elle gère aussi l'ajout de l'annotation **@ResponseBody** aux méthodes portant les services du contrôleur.

Le controleur possède un attribut faisant une référence à la classe de service ApprenantService ou dans notre application **ApprenantRepository** dont la résolution est faite grâce à l'annotation **@Autowired**:

```java
	@Autowired
	private co.simplon.springboot.apprenant.repository.ApprenantRepository apprenantRepository;
```

Sont ensuite définies les méthodes de la classe du contrôleur.

```java
	/**
	 * Retourner tous les apprenants
	 * @return
	 */
	@RequestMapping(value = "/apprenants", method = RequestMethod.GET)
	public ResponseEntity<?> getAllApprenants(){
		List<Apprenant> listeApprenants = null;
		try {
			listeApprenants = apprenantRepository.findAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listeApprenants);
	}
```

Toutes les méthodes de notre API sont précédées de l'annotation **@RequestMapping** qui définira le chemin (fin de l'URL) et la méthode de la requête.
Pour plus d'informations sur les différentes manières d'écrire une annotation **@RequestMapping** , rendez-vous sur http://www.baeldung.com/spring-requestmapping.


**Remarque :** Il possible de renvoyer directement des objets java en retour de fonction. Ces objets sont alors automatiquement sérialisés et renvoyés comme réponse à la requête associée. 

La classe **ResponseEntity** fournie par Spring permet un plus grand contrôle de la réponse HTTP (gestion des codes http, contenu de requête...).

## La vue (HTML et JavaScript)

Comme cela a déjà été évoqué, les éléments relatifs à la vue sont présents dans le répertoire main/src/resources/static. Il s'agit en fait d'un client html / javascript classique qui sera inclu au projet et déployé sur le serveur. Ce client utilisera l'API REST pour accéder aux données des apprenants via des requêtes Ajax.

Pour étudier ce fonctionnement, consulter les fichiers :

 * **index.html** : fichier html point d'entrée du client pour le client web.
 * **apprenant.js** : fichier javascript contenant les fonctions du client web.
 
 **Remarque :** L'API REST que propose notre serveur pourra facilement être utilisés par d'autre clients front. Il serait par exemple possible de l'utiliser via des applications mobiles (android, ios...), vos pages JSP, Thymeleaf ou Angular.

## Observations

Il existe des classes qui ont des fonctions définies :

 * contrôleur
 * repository
 * service
 * entity
 
Il existe une seule vue :

 * elle est en HTML dans un dossier précis
 * elle est envoyée une seule fois au client
 * elle envoie des requêtes Ajax au serveur
 * au retour de la requête, elle met en forme les données

Tout est relié grâce à la magie de Spring et des annotations.

# Tester l'API REST
Dans le cadre du développement d'une API REST, il est nécessaire de pouvoir "émuler" les requêtes qu'appelleront les futurs clients front d'une application. Les 2 outils suivant vous aideront dans ce travail :

 * Curl
 * Postman


## Curl

Curl est un client en ligne de commande permettant de "créer" des requêtes HTTP. On lui passe directement les informations de la requêtes comme arguments lors de l'appel. Très complet, c'est l'"ami" des administrateurs systèmes car on peut facilement l'appeler dans des scripts. Pour en savoir plus sur Curl, rendez-vous sur https://ec.haxx.se/.  

Quelques exemples d'utilisation :
 * curl -i -X POST -H "Content-Type: application/json" -d "{\"key\":\"val\"}" http://localhost:8080/appname/path
 * curl -X POST  -H "Accept: text/plain" -H "Content-Type:application/json" --data @newapprenant.json http://localhost:8080/api/apprenant

## Postman

Postman est un client équivalent à CURL qui fournit une interface graphique plus sympathique que Curl.
Il permet de créer des collections, sauvegarde les requêtes jouées et peut même aller jusqu'à la création de scénarii de test.  

Les liens ci-dessous vous aideront à voir comment il fonctionne :

* [article](https://amethyste16.wordpress.com/2016/02/24/tutoriel-postman/)
* [postman](https://www.getpostman.com/)

# Quelques conseils pour la suite

Spring est un framework difficile à prendre en main car son champ d'application est très vaste. Il fournit une quantité importante de classes utilisables pour différents contextes / besoins et une quantité phénoménale d'annotations auxquelles viennent s'ajouter les annotations des librairies que Spring gère. De plus il est souvent possible d'écrire ces annotations de plusieurs manières. 

Vous l'aurez compris, une grande partie du travail à venir sera donc de se familiariser avec tous ces éléments (les annotations particulièrement).

## Annotations à étudier

Dans la version actuelle de SpringBoot, les annotations ont une importance particulière. Il en existe un grand nombre cependant si vous maîtriser la listes des annotations ci-dessous, vous aurez une base solide pour être autonome. Nous vous conseillons donc d'étudier cette liste que nous avons organisées par "thématique".

### Annotations de configuration

@SpringBootApplication<br>
@Configuration<br>
@ComponentScan<br>
@EnableWebMvc<br>
@Value<br>

### Annotations liées à l'injection de dépendances

@Bean<br>
@Component<br>
@Service<br>
@Repository<br>
@Autowired<br>
@Qualified<br>

### Annotations pour le web

@Controller<br>
@RestController<br>
@RequestMapping<br>
@RequestParam<br>
@RequestBody<br>
@ResponseBody<br>
@PathVariable<br>

## Un peu de lecture

10 raisons pour se mettre à SpringBoot : 

[1ère partie](http://blog.ellixo.com/2015/06/08/10-raisons-de-se-mettre-a-Spring-Boot-1ere-partie.html), 
[2ème partie](http://blog.ellixo.com/2015/06/26/10-raisons-de-se-mettre-a-Spring-Boot-2eme-partie.html)

### Liens spring.io sélectionnés
* [Spring Boot référence guide](https://docs.spring.io/spring-boot/docs/current/reference/html/index.html)
* [Spring Boot référence](http://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/htmlsingle/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/)
* [Consuming a RESTful Web Service with jQuery](https://spring.io/guides/gs/consuming-rest-jquery/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)
* [Consuming a SOAP web service](https://spring.io/guides/gs/consuming-web-service/)
* [Validating Form Input](https://spring.io/guides/gs/validating-form-input/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Integrating Data](https://spring.io/guides/gs/integration/)
* [Testing the Web Layer](https://spring.io/guides/gs/testing-web/)

### webographie
* [spring reference](http://docs.spring.io/spring/docs/5.0.x/spring-framework-reference/html/)
* [guides spring](https://spring.io/guides)
* [doc reference spring](https://spring.io/docs/reference)
* [exemples sur github](https://github.com/netgloo/spring-boot-samples)
* [spring batch](http://blog.octo.com/spring-batch-par-quel-bout-le-prendre/)
* [mvc](http://orm.bdpedia.fr/mvc.html)
* [jackson](https://www.tutorialspoint.com/jackson/index.htm) : Une librairie pour manipuler les objets JSON.
* [une API Spring REST](http://idak.developpez.com/tutoriels/spring/creation-restfull-serviceweb/) en Spring sans Spring-boot.
* [tuto Spring developpez.com](http://rpouiller.developpez.com/tutoriels/spring/application-web-spring-hibernate/)
