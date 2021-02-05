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

${listeErreurs }
<%--TODO: Mettre en page la page d'inscription, afficher les erreurs dans une modal et indiquer les restrictions sur les differents champs--%>
<div class="account-box">

<form action="${pageContext.request.contextPath}/ModifierProfil" method="post">
<p> Pseudo : <input type="text" name="pseudo"> </p>
<p> Nom : <input type="text" name="nom"></p>
<p> Prenom : <input type="text" name="prenom"></p>
<p> Telephone : <input type="text" name="telephone"></p>
<p> Code postal : <input type="text" name="cpo"></p>
<p> Email : <input type="email" name="email"></p>
<p> Rue : <input type="text" name="rue"></p>
<p> Ville : <input type="text" name="ville"></p>
<p> Mot de Passe Actuel : <input type="password" name ="currentPassword"></p>
<p> Nouveau Mot de Passe : <input type="password" name="pw"></p>
<p> Confirmation : <input type="password" name="pw2"></p>

<p> Credit : Faire afficher le nombre de credit restant </p>

<p>
<a href="${pageContext.request.contextPath}/Achat"><button> Enregistrer</button></a>
</p>
</form>

<a href="${pageContext.request.contextPath}/Delete"><button>Supprimer le Compte</button> </a>
</div>

</body>
<%@ include file="footer.html"%>

</html>