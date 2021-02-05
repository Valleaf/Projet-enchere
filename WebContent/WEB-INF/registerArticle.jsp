<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creer une Vente</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    

</head>
<body>


<h1>Créer une Vente</h1>

${listeErreurs }
<form action="${pageContext.request.contextPath}/RegisterArticle" method="post">
	<p><input type="text" name="nomArticle" placeholder="nom de l'article"></p>
	<p><input type="text" name="description" placeholder="description"></p>
	<p>
	<select  name="categorieArticle">
	    <option selected value="0">Choisir</option>
	    <option name="categorieArticle" value="1">Informatique</option>
	    <option name="categorieArticle" value="2">Ameublement</option>
	    <option name="categorieArticle" value="3">Vêtement</option>
	    <option name="categorieArticle" value="4">Sport & Loisirs</option>
	</select>
	</p>
	<div><input type="datetime-local" name="heureDebut" placeholder="Heure Debut"></div>
	<div><input type="datetime-local" name="heureFin" placeholder="Heure de Fin"></div>
	
	<p><input type="text" name="prixInitial" placeholder="Prix initial"></p>
	<p><input type="text" name="image" placeholder="lien de l'image"></p>
	
	<p><input type="submit" value="Creer"></p>
</form>
	<a href="${pageContext.request.contextPath}/"><button>Annuler</button></a>
</body>
<%@ include file="footer.html"%>

</html>