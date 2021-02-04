<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creer un compte</title>
    <link rel="stylesheet" href="css/styles.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    

</head>
<body>

<h1>Mon profil</h1>

${listeErreurs }
<form action="${pageContext.request.contextPath}/Register" method="post">
<p> Pseudo : <input type="text" name="pseudo"></p>
<p> Nom : <input type="text" name="nom"></p>
<p> Prenom : <input type="text" name="prenom"></p>
<p> Telephone : <input type="text" name="telephone"></p>
<p> Code postale : <input type="text" name="cpo"></p>
<p> Email : <input type="email" name="email"></p>
<p> Rue : <input type="text" name="rue"></p>
<p> Ville : <input type="text" name="ville"></p>
<p> Mot De Passe : <input type="password" name="password"></p>
<p> Confirmation Mot de Passe : <input type="password" name="confirmation"></p>
<a href="${pageContext.request.contextPath}/Achat"><button>Créer</button> </a>
<a href="${pageContext.request.contextPath}/Accueil"><button>Annuler</button> </a>


</form>

</body>
</html>