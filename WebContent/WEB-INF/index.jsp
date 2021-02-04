<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>ENI-Encheres : Liste des enchères</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    

</head>
<jsp:include page="header.jsp">
		<jsp:param value="${sessionScope.status}" name="status"/>
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


<%--on fait un foreach sur chaque element de listeArticles qu'on affiche dans une boite--%>

<div class="listeArticles">
	<c:forEach items="${listeArticles}" var="article" varStatus="status" >
		<div class="article" onclick='openDetails(${ article.noArticle })'>
			<div class="articleData">
				<div>nom: <c:out value="${ article.nomArticle }" /></div>
				<div>desc: <c:out value="${ article.description }" /></div>
				<div>date debut: <c:out value="${ article.dateDebut }" /></div>
				<div>date fin: <c:out value="${ article.dateFin }" /></div>
				<div>prix: <c:out value="${ article.prixInitial }" /></div>
			</div>
			<div class="articleImgBox">
					image
			</div>
		</div>
	</c:forEach>
</div>


</body>
<script>

	function openDetails(id){
		window.location = '${pageContext.request.contextPath}/DetailsVente?id='+id;
	}
	
	
</script>
<%@ include file="footer.html"%>

</html>