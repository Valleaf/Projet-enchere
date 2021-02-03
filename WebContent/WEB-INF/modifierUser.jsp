<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier un compte</title>
    <link rel="stylesheet" href="css/styles.css">

</head>
<body>

<h1>Mon profil</h1>
MODIFICATION
${listeErreurs.get(0)}
<form action="${pageContext.request.contextPath}/Modifier" method="post">
<input type="text" name="pseudo">	
<input type="text" name="nom">
<input type="text" name="prenom">
<%--On pourrait demander le mdp actuel --%>
<input type="password" name="password">
<input type="password" name="confirmation">
<input type="text" name="telephone">
<input type="text" name="cpo">
<input type="email" name="email">
<input type="text" name="rue">
<input type="text" name="ville">
<input type="submit" value="Creer">
<a href="${pageContext.request.contextPath}/Accueil"><button>Annuler</button></a>

</form>

</body>
</html>