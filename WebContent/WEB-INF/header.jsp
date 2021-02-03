<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
<%--Affichage de la barre en mode connectee 
sinon affichage de la barre non connectee--%>
<c:when test="${isLoggedIn}">

ENI ENCHERES <%--a gauche --%>

encheres
vendre un article
profil
deconnexion
</c:when>
  <c:otherwise>
  ENI ENCHERES <%--a gauche --%>
  
  <a href="${pageContext.request.contextPath}/Login">Se connecter
  s'inscrire
  </a>
  
  
  
  </c:otherwise>
  </c:choose>


</body>
</html>