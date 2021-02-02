<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>ENI-Encheres : Liste des enchères</title>
    <link rel="stylesheet" href="css/styles.css">

</head>
<jsp:include page="header.jsp">
		<jsp:param value="${session.status}" name="isLoggedIn"/>
</jsp:include>


<body>

<form action ="${pageContext.request.contextPath}" method = "get">
<input type="text" name="nomArticleFiltre" placeholder="articles contenant...">
<select  name="categorieArticleFiltre">
    <option selected>Toutes</option>
    <option name="categorieArticleFiltre" value="">Informatique</option>
    <option name="categorieArticleFiltre" value="">Ameublement</option>
    <option name="categorieArticleFiltre" value="">Vêtement</option>
    <option name="categorieArticleFiltre" value="">Sport & Loisirs</option>
</select>
<input type="submit">
</form>


<%--on fait un foreach sur chaque element de listeArticles qu'on affiche dans une boite
c:forEach items="${listeArticles}">
<div>listeArticles.identifiant
etc
</div>
</c:foreach>
--%>


</body>

<%@ include file="footer.html"%>

</html>