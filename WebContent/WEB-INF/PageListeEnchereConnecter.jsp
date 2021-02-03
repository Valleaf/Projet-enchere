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

<input type="checkbox" name="Achat">
	
	<p>Achats</p>

<div>
  <input type="radio" name="Achat"
         checked>
  <label>Enchères ouvertes</label>
</div>

<div>
  <input type="radio"  name="Achat">
  <label>Mes Enchères</label>
</div>

<div>
  <input type="radio" name="Achat">
  <label>Mes enchères remportées</label>
</div>

<input type="checkbox" name="Mes Ventes">
	
	<p>Mes Ventes</p>

<div>
  <input type="radio" name="ventes en cours"
         checked>
  <label>Ventes en cours</label>
</div>

<div>
  <input type="radio"  name="vente non débutées">
  <label>Ventes non débutées</label>
</div>

<div>
  <input type="radio" name="Ventes terminée">
  <label>Ventes terminées</label>
</div>
	
<input type="submit" value="Rechercher">
</form>




