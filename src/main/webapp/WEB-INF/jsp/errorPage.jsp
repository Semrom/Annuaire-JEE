<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="home" value="/annuaire/accueil/" />

<!DOCTYPE html>
<html>
	<head>
		<title>Annuaire | Erreur <c:out value="${errorCode}" /></title>
		<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
	</head>
	
	<body>
		<!-- Menu -->
		<%@ include file="/WEB-INF/jsp/menu.jsp"%>
		
		<div class="container">
			<div class="jumbotron marge-haut">
  				<h2><c:out value="${infoMessage}" /></h2>
  				<p><c:out value="${errorMessage}" /></p>
  				<p><a class="btn btn-primary btn-lg" href="${home}">Retour à l'annuaire</a></p>
			</div>
		</div>
			
		<!-- Pied de page -->
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	</body>
</html>