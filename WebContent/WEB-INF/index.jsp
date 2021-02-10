<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>ENI-Encheres : Liste des enchères</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/vendor/bootstrap/css/bootstrap.min.css">

</head>
	<jsp:include page="header.jsp">
			<jsp:param value="${sessionScope.status}" name="status"/>
	</jsp:include>
<body>

<%--on fait un foreach sur chaque element de listeArticles qu'on affiche dans une boite--%>

<div class="container">
	<div class="listeArticles">
		<c:forEach items="${listeArticles}" var="article" varStatus="status" >
			<div class="article" onclick='openDetails(${ article.noArticle })'>
				<c:set value="${ article.etatVente }" var="etatVente" />
				
				<fmt:parseDate value="${ article.dateDebut }" var="dateDebut"
				                pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:parseDate value="${ article.dateFin }" var="dateFin"
				                pattern="yyyy-MM-dd HH:mm:ss" />
				<div class="articleImgBox">
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
					<img alt="placeholder Image" src="${pageContext.request.contextPath}/img/article.png">
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
					   		<div><c:out value="${ article.prixInitial }" /> points</div>
					   	</c:when> 
					   	<c:otherwise>
					   		<div><c:out value="${ article.prixVente }" /> points</div>
					   	</c:otherwise>
					</c:choose>
				</div>
	
			</div>
		</c:forEach>
	</div>
</div>

</body>

<script>

	function openDetails(id){
		window.location = '${pageContext.request.contextPath}/DetailsVente?id='+id;
	}
	
	
</script>
<%@ include file="footer.html"%>

</html>