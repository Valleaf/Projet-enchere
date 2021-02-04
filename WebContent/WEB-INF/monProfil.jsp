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
<h1 class="page-title">Mon  Profil</h1>
<div class="account-box">
<p class="profil-text"> Pseudo : ${user.pseudo}</p>
<p class="profil-text"> Nom : ${user.nom} </p>
<p class="profil-text">Prenom : ${user.prenom} </p>
<p class="profil-text">Email : ${user.email} </p>
<p class="profil-text">Telephone : ${user.telephone}</p> 
<p class="profil-text">Rue : ${user.rue} </p>
<p class="profil-text">Code Postal : ${user.cpo} </p>
<p class="profil-text">Ville : ${user.ville} </p>

<p><a href="${pageContext.request.contextPath}/ModifierProfil"><button class="bouton-modifier">Modifier</button></a></p>
</div>
</body>


<%@ include file="footer.html"%>
</html>