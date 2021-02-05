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

<%--Affichage de la barre en mode connectee 
sinon affichage de la barre non connectee--%>
<%--TODO: Bug , parfois la barre en mode connecté s'affiche, cela ne devrait pas etre le cas --%>
 
  <div class="nav-bar">

  <a class="active" href="${pageContext.request.contextPath}/">ENI Enchères</a>
  <div class="right">
  <c:choose>
<c:when test="${status == 'Connecté'}">
    <a href="${pageContext.request.contextPath}/RegisterArticle">Vendre un article</a>
    <a href="${pageContext.request.contextPath}/MaPageProfil">Profil</a>
    <a href="${pageContext.request.contextPath}/Deconnexion">Se Deconnecter</a>
    </c:when>
      <c:otherwise>
    
    <a href="${pageContext.request.contextPath}/Login">Se connecter / S'inscrire</a>
      </c:otherwise>
  </c:choose>
  </div>

</div>
  
  
  



</body>
</html>