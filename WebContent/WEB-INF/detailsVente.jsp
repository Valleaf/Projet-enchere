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
				<div>prix: <c:out value="${ dataArticle.prixInitial }" /></div>
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