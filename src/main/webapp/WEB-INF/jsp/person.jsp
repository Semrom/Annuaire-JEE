<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="home" value="/annuaire/accueil/" />
<c:url var="group" value="/annuaire/accueil/groupe/" />
<c:url var="edit" value="/annuaire/personne/modifier" />

<!DOCTYPE html>
<html>
	<head>
		<title>Annuaire | <c:out value="${person.firstname} ${person.lastname}" /></title>
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
	            		<h1>Profil de <c:out value="${person.firstname} ${person.lastname}" /></h1>
	          		</div>
	        	</div>
	        	<div class="row">
	        		<div class="col-lg-12">
	        			<a class="btn btn-info" href="${group}${person.groupId}">Voir le groupe <c:out value="${groupName}" /></a>
	            		<a class="btn btn-info" href="${home}">Retour à l'annuaire</a>
	            	</div>
	        	</div>
	      	</div>
			<hr />
			
			<!-- Informations de la personne -->
			<div class="panel panel-primary">
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        	<tr>
	                        	<th>Nom</th>
	                        	<th>Prénom</th>
	                        	<th>Site web</th>
	                        	<th>Appartient au groupe</th>
	                        </tr>
                        </thead>
                        <tbody>
                        	<tr>
                        		<td><c:out value="${person.lastname}" /></td>
                        		<td><c:out value="${person.firstname}" /></td>
                        		<td>
                        			<c:choose>
                        				<c:when test="${person.website == 'Aucun'}">
                        					<c:out value="${person.website}" />
                        				</c:when>
                        				<c:otherwise>
                        					<a href="http://${person.website}"><c:out value="${person.website}" /></a>
                        				</c:otherwise>
                        			</c:choose>
                        		</td>
                        		<td><a href="${group}${person.groupId}"><c:out value="${groupName}" /></a></td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
           	</div>
           	
           	<c:if test="${user.name != null && user.id == person.id}">
           		<a class="btn btn-primary" href="${edit}?profil=${person.id}">Modifier le profil</a>
           	</c:if>
           	
		</div>
			
		<!-- Pied de page -->
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	</body>
</html>