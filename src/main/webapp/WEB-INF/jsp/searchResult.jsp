<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="home" value="/annuaire/accueil/" />
<c:url var="showPersonList" value="/annuaire/accueil/groupe/" />
<c:url var="profile" value="/annuaire/personne/profil/" />

<!DOCTYPE html>
<html>
	<head>
		<title>Annuaire | Recherche de <c:out value="${option}" /></title>
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
	            		<h2>Résultat de la recherche de <c:out value="${option}" /> pour "<c:out value="${search}" />"</h2>
	          		</div>
	        	</div>
	        	<div class="row">
	        		<div class="col-lg-12">
	            		<a class="btn btn-info" href="${home}">Retour à l'annuaire</a>
	            	</div>
	        	</div>
	      	</div>
			<hr />
			
			<c:choose>
				<c:when test="${message != null}">
					<h2 class="centrer"><c:out value="${message}" /></h2>
				</c:when>
				<c:otherwise>
					<table class="table table-hover table-striped">
						<thead>
		  					<tr>
		    					<th>Noms</th>
		    				</tr>
		    			</thead>
		    				<c:choose>
							<c:when test="${option == 'groupes'}">
			     				<tbody>
			     					<c:forEach items="${groups}" var="group">
			     						<tr>
			     							<td><a href="${showPersonList}${group.id}"><c:out value="${group.name}" /></a></td>
			     						</tr>
			     					</c:forEach>
			     				</tbody>
			     			</c:when>
			     			<c:otherwise>
			     				<tbody>
			     					<c:forEach items="${persons}" var="person">
			     						<tr>
			     							<td><a href="${profile}${person.id}"><c:out value="${person.firstname} ${person.lastname}" /></a></td>
			     						</tr>
			     					</c:forEach>
			     				</tbody>
			     			</c:otherwise>
		     			</c:choose>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
			
		<!-- Pied de page -->
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	</body>
</html>