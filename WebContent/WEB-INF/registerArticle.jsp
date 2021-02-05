<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creer un compte</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    

</head>
<body>


<h1>Ajout d'article</h1>


<form action="${pageContext.request.contextPath}/RegisterArticle" method="post">
	<input type="text" name="nomArticle" placeholder="nom de l'article">
	<input type="text" name="description" placeholder="description">
	
	<input type="datetime-local" name="heureDebut" placeholder="Heure Debut">
	<input type="datetime-local" name="heureFin" placeholder="Heure de Fin">
	
	<input type="text" name="prixInitial" placeholder="Prix initial">
	<input type="text" name="image" placeholder="lien de l'image">
	
	<input type="submit" value="Creer">
<a href="${pageContext.request.contextPath}/Accueil"><button>Annuler</button></a>

</form>

</body>
</html>