<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier mon profil</title>
<link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/header.css">
</head>
<jsp:include page="header.jsp">
		<jsp:param value="${session.status}" name="isLoggedIn"/>
</jsp:include>
<body class="container-profil">

<h1 class="page-title">Modifier mon profil</h1>

<div class="account-box">

<form action="${pageContext.request.contextPath}/Register" method="post">
<p> Pseudo : <input type="text" name="pseudo"> </p>
<p> Nom : <input type="text" name="nom"></p>
<p> Prenom : <input type="text" name="prenom"></p>
<p> Telephone : <input type="text" name="telephone"></p>
<p> Code postal : <input type="text" name="cpo"></p>
<p> Email : <input type="email" name="email"></p>
<p> Rue : <input type="text" name="rue"></p>
<p> Ville : <input type="text" name="ville"></p>
<p> Mot de Passe Actuel : <input type="password" name ="motDePasseActuel"></p>
<p> Nouveau Mot de Passe : <input type="password" name="nouveauPassword"></p>
<p> Confirmation : <input type="password" name="confirmation"></p>

<p> Credit : Faire afficher le nombre de credit restant </p>

<p>
<a href="${pageContext.request.contextPath}/Achat"><button> Enregistrer</button></a>
<%-- Les informations doivent se mettre a jour au niveau de la BDD --%> 
<a href="${pageContext.request.contextPath}/Accueil"><button>Supprimer le Compte</button> </a>
<%-- Le compte utilisateur doit etre supprimer  --%>
</p>
</form>
</div>

</body>
<%@ include file="footer.html"%>

</html>