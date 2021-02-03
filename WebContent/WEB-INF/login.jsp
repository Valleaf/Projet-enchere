<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page de connexion</title>
    <link rel="stylesheet" href="css/styles.css">

</head>
<body>
<jsp:include page="header.jsp">
		<jsp:param value="${session.isLoggedIn}" name="isLoggedIn"/>
</jsp:include>


<form action="${pageContext.request.contextPath}/Login" method="post"> 
<input type="text" name="pseudo">
<input type="password" name="password">
<input type="submit"value="Connexion">
<input type="checkbox" name="souvenir">
</form>
<a href="motedepasseoublie">Mote de passe oublie</a>

<a href="${pageContext.request.contextPath}/Register">
<button>creeer un compte</button>
</a>


<%@ include file="footer.html"%>
</body>
</html>