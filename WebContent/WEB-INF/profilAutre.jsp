<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon Profil</title>
    <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/header.css">
    


</head>
<jsp:include page="header.jsp">
		<jsp:param value="${session.status}" name="isLoggedIn"/>
</jsp:include>
<body class="container-profil">
<h1 class="page-title">Profil TEST</h1>
<div class="account-box">
<p class="profil-text"> Pseudo : ${dataUser.pseudo}</p>
<p class="profil-text"> Nom : ${dataUser.nom} </p>
<p class="profil-text">Prenom : ${dataUser.prenom} </p>
<p class="profil-text">Email : ${dataUser.email} </p>
<p class="profil-text">Telephone : ${dataUser.telephone}</p> 
<p class="profil-text">Rue : ${dataUser.rue} </p>
<p class="profil-text">Code Postal : ${dataUser.cpo} </p>
<p class="profil-text">Ville : ${dataUser.ville} </p>
<c:if test="${user.admin == true}">
<form action="${pageContext.request.contextPath}/Delete" method="post">
Supprimer ce compte?
<input type="hidden" value="${dataUser.numero}" name="idUser">
<input type="submit" value="Supprimer">
</form>
</c:if>
</div>
</body>



<%@ include file="footer.html"%>
</html>