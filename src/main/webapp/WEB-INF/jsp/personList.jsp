<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="home" value="/annuaire/accueil/" />
<c:url var="profile" value="/annuaire/personne/profil/" />

<!DOCTYPE html>
<html>
	<head>
		<title>Annuaire | Groupe <c:out value="${groupName}" /></title>
		<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
	</head>
	
	<body>
		<!-- Menu -->
		<%@ include file="/WEB-INF/jsp/menu.jsp"%>
		
		<div class="container">
		
			<!-- En-tête de la page -->
	      	<div class="page-header" id="banner">
	        	<div class="row">
	          		<div class="col-lg-8 col-md-7 col-sm-6">
	            		<h1>Groupe <c:out value="${groupName}" /></h1>
	          		</div>
	        	</div>
	        	<div class="row">
	        		<div class="col-lg-12">
	            		<a class="btn btn-info" href="${home}">Retour à l'annuaire</a>
	            	</div>
	        	</div>
	      	</div>
			<hr />
			
			<!-- Liste des personnes -->
			<table class="table table-hover table-striped">
				<thead>
   					<tr>
     					<th>Noms</th>
     				</tr>
     				</thead>
     				<tbody>
     					<c:forEach items="${persons}" var="person">
     						<tr>
     							<td><a href="${profile}${person.id}"><c:out value="${person.firstname} ${person.lastname}" /></a></td>
     						</tr>
     					</c:forEach>
     				</tbody>
			</table>
		</div>
			
		<!-- Pied de page -->
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	</body>
</html>