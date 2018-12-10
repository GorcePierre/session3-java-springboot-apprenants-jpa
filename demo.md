# Démonstration d'un projet Back SpringBoot avec JavaScript en Front

![exemple-spring-boot-js-apprenants.png](../images/exemple-spring-boot-js-apprenants.png)

## Pour la partie JAVA

Liste des méthodes appelées par ajax-jquery qui se trouvent dans le controleur **ApprenantController** via les URL définies dans les annotations
**@RequestMapping()** :

```java
@RequestMapping(value = "/apprenants", method = RequestMethod.GET)
	public ResponseEntity<?> getAllApprenants()

@RequestMapping(value = "/apprenant/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getApprenant(@PathVariable Integer id)

@RequestMapping(value = "/apprenant", method = RequestMethod.POST)
	public ResponseEntity<?> addApprenant(@RequestBody Apprenant apprenant)

@RequestMapping(value = "/apprenant/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateApprenant(@RequestBody Apprenant apprenant,@PathVariable Integer id)

@RequestMapping(value = "/apprenant/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteApprenant(@PathVariable Integer id)

```

>Remarques : 2 choses importantes, les méthodes contiennent des paramètres de type **RequestMethod** et l'URL. Mais cela, vous l'avez certainement déjà vu avec SpringBoot.

## Technologie utilisée : AJAX / JQUERY et DataTable

[lien vers l'objet DataTable utilisé dans l'exemple](https://datatables.net/)

![datatable.png](../images/datatable.png)

[lien vers W3schools sur AJAX](https://www.w3schools.com/js/js_ajax_intro.asp)

## Exemple et explications

- page html qui contient le formulaire et l'objet DataTable
- script en javascript qui permet de faire le lien avec le back grace à AJAX et le format JSON (API Resy côté back)

Page HTML : **index.html**

```html
<!DOCTYPE html>
<html>
<head>
<title>Base de données Apprenants LaPoste Promo3</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.13/datatables.min.css" />
</head>
<body>
	<header id="header">
		<div class="inner">
			<a href="index.html" class="logo"> <span class="symbol"><img
					src="image/logo-simplon.png" alt="Logo Simplon.co" /></span><span
				class="title">Chez Simplon il fait bon...</span>
			</a>
		</div>
	</header>

	<section id="sectionApprenant" class="container" style="min-height: 500px">
		<header>
			<h1>BD Apprenants Promo3 (version JPA et hibernate en Back)</h1>
		</header>
		<hr>
		<article class="col-sm-6 col-xs-12" id="apprenantDetail">
		
			<form class="form-horizontal" id="apprenant-form">
			<fieldset>
				<div class="form-group form-group-lg">
					<label class="col-sm-2 control-label">id</label>
					<div class="col-sm-4">
						<input type="number" tabindex="1"  placeholder="identifiant"  title="identifiant" class="form-control" id="id" />
					</div>
				</div>
					<div class="form-group form-group-lg">
					<label class="col-sm-2 control-label">Prénom</label>
					<div class="col-sm-10">
						<input type="text" tabindex="2"  class="form-control" id="prenom" />
					</div>
				</div>
				<div class="form-group form-group-lg">
					<label class="col-sm-2 control-label">Nom</label>
					<div class="col-sm-10">
						<input type="text" tabindex="3"  class="form-control" id="nom" />
					</div>
				</div>
				<div class="form-group form-group-lg">
					<label class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input type="text" tabindex="4"  class="form-control" id="email" />
					</div>
				</div>
			</fieldset>
			</form>
			<div class="form-group">
				<div class="col-sm-12">
					<button id="btn-get" class="btn btn-primary btn-lg" title="Rechercher" >GET</button>
					<button id="btn-post" class="btn btn-primary btn-lg" title="Ajouter">POST</button>
					<button id="btn-put" class="btn btn-primary btn-lg" title="Modifier">PUT</button>
					<button id="btn-delete" class="btn btn-primary btn-lg" title="Supprimer">DELETE</button>
					<button id="btn-reset" class="btn btn-primary btn-lg" title="Effacer">RESET</button>
				</div>
			</div>
			<div id="feedbackapprenant"></div>
		</article>
		
		<article class="col-sm-6 col-xs-12" id="apprenantList">
			<table id="apprenantTable" class="display">
				<thead>
					<tr>
						<th>id</th>
						<th>prénom</th>
						<th>nom</th>
						<th>Email</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>id</th>
						<th>prénom</th>
						<th>nom</th>
						<th>Email</th>
					</tr>
				</tfoot>
			</table>
		</article>
	</section>
	<footer id="footer" class="container">
		<p>
			© <a href="http://www.simplon.co">simplon.co</a> 2018
		</p>
	</footer>
	<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.13/datatables.min.js"></script>
	<script type="text/javascript" src="js/apprenant.js"></script>
</body>
</html>
```

Page Javascript : **apprenant.js**

```js
$(document).ready(function() {

	// appelle la méthode pour charger la base données dans la datatable
	loadDatatable();
	
	// déclaration d'une variable table;
	var table = $('#apprenantTable').DataTable();
	
	/* Si vous double-cliquez sur une ligne de la datatable
	   vous récupérez la valeur des attributs de la ligne concernée (row)
	   aux différentes zones de saisie
	*/
	$('#apprenantTable tbody').on( 'dblclick', 'tr', function () {
	    let dataRow = table.row( this ).data();
	    $("#id").val(dataRow.id);
		$("#prenom").val(dataRow.prenom);
		$("#nom").val(dataRow.nom);
		$("#email").val(dataRow.email);
	} );
	
	// si vous cliquez sur le bouton click "btn-post"
	// on appelle la méthode "apprenant_submit()
	// en lui passant 2 paramètres : la référence du bouton pour le désactiver et la type de méthode, ici POST.
	$("#btn-post").click(function() {
		apprenant_submit($("#btn-post"), "POST", table);
	
	});

	// si vous cliquez sur le bouton click "btn-put"
	// on appelle la méthode "apprenant_submit()
	// en lui passant 2 paramètres : la référence du bouton pour le désactiver et la type de méthode, ici PUT.
	$("#btn-put").click(function() {
		apprenant_submit($("#btn-put"), "PUT", table);
	
	});

	//click on RESET
	$("#btn-reset").click(function() {
		resetForm(); // méthode qui met les valeurs des zones de textes du formulaire à blanc
		resetFeedBackApprenant();
	});

	//click on GET
	$("#btn-get").click(function() {
		getApprenant(); // affiche l'apprenant(e) sélectionné(e) dans la DataTable
	});

	//click on DELETE
	$("#btn-delete").click(function() {
		deleteApprenant(); // efface l'apprenant en fonction de l'identifiant
		
		
	});
});

/**
 * Charge les données dans la DataTable (JQuery)
 * @returns
 */
function loadDatatable() {
	$('#apprenantTable').DataTable({
		"columnDefs": [
	            {
	                "targets": [ 0 ],
	                "sortable" : false
	            },
	            {
	                "targets": [ 3 ],
	                "visible": true
	            }
	        ],
		"ajax" : {
			url : '/api/apprenants',
			dataSrc : ''
		},
		"columns" : [ 
			{"data" : "id"},
			{"data" : "prenom"},
			{"data" : "nom"}, 
			{"data" : "email"} ]
	});
	
}
/**
 * Méthode qui traite les POST et PUT
 * @param button
 * @param httpVerb
 * @returns
 */
function apprenant_submit(button, httpVerb, table) {

	var apprenant = {};
	// on récupère les valeurs saisies
	apprenant["id"] = $("#id").val();
	apprenant["prenom"] = $("#prenom").val();
	apprenant["nom"] = $("#nom").val();
	apprenant["email"] = $("#email").val();
	
	// on initialise l'url du back
	var url = "/api/apprenant";
	
	// si c'est une modification, on passe l'identifiant
	if(httpVerb == "PUT")
		url += "/" + apprenant["id"];
	
	// on désactive le bouton en cours 
	button.prop("disabled", true);

	// on lance la méthode ajax pour faire le lien avec les méthodes back du constructeur
	$.ajax({
		type : httpVerb,						// méthode POST ou PUT
		contentType : "application/json",		// type de données
		url : url,								// url destinatrice
		data : JSON.stringify(apprenant),		// on transforme les données de la variable Javascript "apprenant" au format JSON
		dataType : 'json',						// on précise le mode de transfert
		cache : false,							// pas de cache sollicité
		timeout : 600000,						// délai d'attente
		success : function(data) {				// si ok

			var json = "<h3>Server Response au format JSON</h3><pre>apprenant (modifié/ajouté) :<br>" + JSON.stringify(data, null, 4) + "</pre>";
			
			$('#feedbackapprenant').html(json); // renvoie les infos au format JSON adapté au HTML dans la balise "<DIV id="feedbackapprenant"> 

			console.log("SUCCESS : ", data);
			button.prop("disabled", false);

			resetForm()
		},
		// si erreur que fait-on ?
		error : function(e) {

			var json = "<h3>Server Response</h3><pre>" + e.responseText	+ "</pre>";
			
			$('#feedbackapprenant').html(json);

			console.log("ERROR : ", e);
			button.prop("disabled", false);

		}
	});
	
	table.ajax.reload(); // on recharge les données via ajax et la méthode reload()
}

function resetForm() {
	$('#apprenant-form')[0].reset();
}

function resetFeedBackApprenant() {
	$('#feedbackapprenant').html("");
}

/**
 * Méthode qui récupère un apprenant
 * @returns
 */
function getApprenant() {

	var idApprenant = $("#id").val(); // on récupère la variable

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/api/apprenant/" + idApprenant,
		data : {},
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h3>Server Response format JSON</h3><pre>Apprenant trouvé :<br>" + JSON.stringify(data, null, 4) + "</pre>";
			$('#feedbackapprenant').html(json);
			$("#id").val(data.id);
			$("#prenom").val(data.prenom);
			$("#nom").val(data.nom);
			$("#email").val(data.email);
			console.log("SUCCESS : ", data);
		},
		error : function(e) {

			var json = "<h3>Server Response</h3><pre>" + e.responseText	+ "</pre>";
			
			$('#feedbackapprenant').html(json);

			console.log("ERROR : ", e);
		}
	});
}

/**
 * méthode pour supprimer un apprenant
 * @returns
 */
function deleteApprenant() {

	var idApprenant = $("#id").val();

	$.ajax({
		type : "DELETE",
		contentType : "application/json",
		url : "/api/apprenant/" + idApprenant,
		//data : {},
		//dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h3>Server Response</h3><pre>Apprenant " + idApprenant + " deleted.</pre>";
			$('#feedbackapprenant').html(json);
			console.log("SUCCESS : ", data);

			resetForm();
		},
		error : function(e) {
			var json = "<h3>Server Response</h3><pre>" + e.responseText	+ "</pre>";
			
			$('#feedbackapprenant').html(json);
			console.log("ERROR : ", e);
		}
	});
}
```
C'est un peu long, mais ça vaut le coup de jeter un oeil !

## Your Job...

Faites la même chose avec la table de votre choix, voire l'application SpringBoot sur les Films que vous avez déjà réalisée la semaine dernière.

Comme cela, pour ceux et celles qui n'auraient pas encore d'interface graphique, vous allez pouvoir visualiser dans une page HTML grâce à javascript vos données.

A vous de jouer...

[projet java-springboot-apprenants-jpa](java-springboot-apprenants-jpa/)