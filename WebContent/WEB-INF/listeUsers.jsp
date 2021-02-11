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
test
		<c:forEach items="${listeUsers}" var="user">
		<div>
		${user.pseudo}
<c:if test="${user.admin != true }">
		 Supprimer
		</c:if>
		</div>
		
		
		</c:forEach>



</body>
</html>