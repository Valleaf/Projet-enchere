<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creer une Vente</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    

</head>
<body>
<jsp:include page="header.jsp">
		<jsp:param value="${session.isLoggedIn}" name="isLoggedIn"/>
</jsp:include>
<c:set var="userAgent" value="${header['user-agent']}"/>

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
	
	 <c:if test="${fn:containsIgnoreCase(userAgent, 'Chrome')}">
	 	<div><input type="datetime-local" name="heureDebut" placeholder="Heure Debut"></div>
	<div><input type="datetime-local" name="heureFin" placeholder="Heure de Fin"></div>
 </c:if>
 <c:if test="${!fn:containsIgnoreCase(userAgent, 'Chrome')}">
 	Integration sur firefox non faite actuellement
  	<div><input type="date" name="dateDebut" placeholder="Date Debut"></div>
	<div><input type="date" name="dateFin" placeholder="Date Fin"></div>
	<div><input type="time" name="heureDebutFirefox" placeholder="Heure Debut"></div>
	<div><input type="time" name="heureFinFirefox" placeholder="Heure Fin"></div>
 </c:if>
	<p><input type="text" name="prixInitial" placeholder="Prix initial"></p>
	<p><input type="text" name="image" placeholder="lien de l'image"></p>
	
	<p><input type="submit" value="Creer"></p>
</form>
	<a href="${pageContext.request.contextPath}/"><button>Annuler</button></a>
</body>
<%@ include file="footer.html"%>

</html>