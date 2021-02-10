<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page de connexion</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    

</head>
<body>
<jsp:include page="header.jsp">
		<jsp:param value="${session.isLoggedIn}" name="isLoggedIn"/>
</jsp:include>
<%-- TODO : AFFICHER LE MESSAGE D'ERREUR PROPREMENT --%>
${messageerreur }

<div class="container-login">

<form class="login-form" action="${pageContext.request.contextPath}/Login" method="post">
<%--Version basique --%>
  <c:if test="${cookie.loginAuto.getValue() != true}">
  <h2>Se Connecter</h2>
  <div class="form-input-material">
  <label for="pseudo">Pseudo</label>
  <br>
    <input type="text" name="pseudo" autocomplete="off" class="form-control-material" required />
    
  </div>
  <div class="form-input-material">
  <label for="password">Mot de Passe</label><br>
    <input type="password" name="password"  autocomplete="off" class="form-control-material" required />
    
  </div>
  <label for="remember">Se souvenir du login ?</label>
  <input type="checkbox" name="remember">
  <button type="submit" class="login-button">Login</button>
  </c:if>
  <%--Version avec cookie Se souvenir du login --%>
  <c:if test="${cookie.loginAuto.getValue() == true}">
  <h2>Se Connecter</h2>
  <div class="form-input-material">
  <label for="pseudo">Pseudo</label>
  <br>
    <input type="text" name="pseudo" value="${cookie.login.getValue()}" autocomplete="off" class="form-control-material" required />
    
  </div>
  <div class="form-input-material">
  <label for="password">Mot de Passe</label><br>
    <input type="password" name="password" value="${cookie.pw.getValue()}" autocomplete="off" class="form-control-material" required />
    
  </div>
  <label for="remember">Se souvenir du login ?</label>
  <input type="checkbox" name="remember">
  <button type="submit" class="login-button">Login</button>
  </c:if>
  
</form>
 <a href="${pageContext.request.contextPath}/Register">Mot de passe oublie</a>

<a href="${pageContext.request.contextPath}/Register">
<button>creeer un compte</button>
</a>

  </div>




<%@ include file="footer.html"%>
</body>
</html>