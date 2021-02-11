<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon Profil</title>
    <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/header.css">
    <title>ENI-Encheres : Liste des enchères</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/vendor/bootstrap/css/bootstrap.min.css">
    


</head>
<jsp:include page="header.jsp">
		<jsp:param value="${session.status}" name="isLoggedIn"/>
</jsp:include>
<body>
<div class="profilContainer">
	<div class="container-profil">
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
			<p class="profil-text">Credit : ${user.credit} </p>
			
			<p><a href="${pageContext.request.contextPath}/ModifierProfil"><button class="bouton-modifier">Modifier</button></a></p>
		</div>
	</div>
		<div class="myListeArticles">
			<c:forEach items="${listeArticles}" var="article" varStatus="status" >
				<div class="article" onclick='openDetails(${ article.noArticle })'>
					<c:set value="${ article.etatVente }" var="etatVente" />
					
					<fmt:parseDate value="${ article.dateDebut }" var="dateDebut"
					                pattern="yyyy-MM-dd HH:mm:ss" />
					<fmt:parseDate value="${ article.dateFin }" var="dateFin"
					                pattern="yyyy-MM-dd HH:mm:ss" />
					<div class="myArticleImgBox">
							<c:choose>
						   		<c:when test="${ etatVente.equals('CR') }">
									<div class="etatVente bientot"><c:out value="PROCHAINEMENT" /></div>
								</c:when> 
						   		<c:when test="${ etatVente.equals('EC') }">
									<div class="etatVente ouvert"><c:out value="VENTE EN COURS" /></div>
								</c:when> 
						   	<c:otherwise>
								<div class="etatVente terminer"><c:out value="VENTE TERMINEE" /></div>
						   	</c:otherwise>
						</c:choose>
						<img src="${article.image}" alt="Image de l'enchere" width="100px">
						<%-- <img alt="placeholder Image" src="${pageContext.request.contextPath}/img/article.png">--%>
					</div>
					<div class="articleData">
						<div><c:out value="${ article.nomArticle }" /></div>
						<div><c:out value="${ article.description }" /></div>
						<c:choose>
						   	<c:when test="${ etatVente.equals('CR') }">
								<div><fmt:formatDate value="${dateDebut }" pattern="dd/MM/yyyy hh:mm" /></div>
								<div><c:out value="${ article.prixInitial }" /> points</div>
							</c:when> 
						   	<c:when test="${ etatVente.equals('EC') }">
						   		<div><fmt:formatDate value="${dateFin }" pattern="dd/MM/yyyy hh:mm" /></div>
						   		<c:choose>
							   		<c:when test="${article.prixVente < article.prixInitial }">
							   			<div><c:out value="${ article.prixInitial }" /> points</div>
							   		</c:when>
							   		<c:otherwise>
							   			
										<div><c:out value="${ article.prixVente }" /> points</div>
							   		</c:otherwise>
						   		</c:choose>
						   	</c:when> 
						   	<c:otherwise>
						   						   		<div><fmt:formatDate value="${dateFin }" pattern="dd/MM/yyyy hh:mm" /></div>
						   	
	<c:choose>
							   		<c:when test="${article.prixVente < article.prixInitial }">
							   			<div><c:out value="${ article.prixInitial }" /> points</div>
							   		</c:when>
							   		<c:otherwise>
							   		
										<div><c:out value="${ article.prixVente }" /> points</div>
							   		</c:otherwise>
						   		</c:choose>					   	</c:otherwise>
						</c:choose>
					</div>
		
				</div>
			</c:forEach>
		</div>
</div>
</body>


<%@ include file="footer.html"%>
</html>