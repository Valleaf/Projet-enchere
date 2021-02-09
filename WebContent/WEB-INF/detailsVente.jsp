<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details de la Vente</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">


</head>
<jsp:include page="header.jsp">
		<jsp:param value="${session.status}" name="isLoggedIn"/>
</jsp:include>


<body>
  </a>
<div class="listeArticles">
		<div class="article">
			<div class="articleData">
				<div>nom: <c:out value="${ dataArticle.nomArticle }" /></div>
				<div>desc: <c:out value="${ dataArticle.description }" /></div>
				<div>date debut: <c:out value="${ dataArticle.dateDebut }" /></div>
				<div>date fin: <c:out value="${ dataArticle.dateFin }" /></div>
				<div>prix initial: <c:out value="${ dataArticle.prixInitial }" /></div>
				<c:if test="${enchereActive.prixEnchere != null }">
				<div>prix actuel: <c:out value="${ enchereActive.prixEnchere }" /></div>
				</c:if>
		
				
		
				
				<c:if test="${(0 <=  (user.credit-enchereActive.prixEnchere + 1 ))&& !over && started}">
				Enchere possible :
				<div>
				<%--TODO  Message d'erreur quand pas assez de credits--%>
				<form action="${pageContext.request.contextPath}/Encherir">
				  <input type="hidden"  name="articleID" value="${dataArticle.noArticle}"> 
				  <c:if test="${enchereActive.noUser == user.numero }">
				<input type="number" name="encherePrix" min="${enchereActive.prixEnchere +1 }" max="${ user.credit + enchereActive.prixEnchere}" >
				</c:if>
				  <c:if test="${enchereActive.noUser != user.numero }">
				<input type="number" name="encherePrix" min="${enchereActive.prixEnchere +1 }" max="${ user.credit}" >
				</c:if>				<input type="submit">
				</form></div>
				
				</c:if>
				<div><a href="${pageContext.request.contextPath}/Profils?id=${ dataArticle.noUtilisateur }">Utilisateur: <c:out value="${ dataArticle.noUtilisateur }" /></a></div>
				
			</div>
			<div class="articleImgBox">
					image
			</div>
		</div>
</div>

</body>
<%@ include file="footer.html"%>

</html>