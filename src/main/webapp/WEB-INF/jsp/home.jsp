<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="showPersonList" value="/annuaire/accueil/groupe/" />
<c:url var="searching" value="/annuaire/recherche/" />

<!DOCTYPE html>
<html>
	<head>
		<title>Annuaire | Accueil</title>
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
	            		<h1>Bienvenue sur l'annuaire</h1>
	          		</div>
	        	</div>
	      	</div>
			<hr />
			
			<!-- Zone de recherche -->
			<h3>Recherche</h3>
			<form class="form-horizontal" method="GET" action="${searching}">
				<fieldset>
					<div class="form-group">
			  			<label class="col-lg-2 control-label" for="inputDefault">Recherche : </label>
			  			<div class="col-lg-10">
			  				<input name="search" type="text" class="form-control" id="inputDefault" maxlength="50" placeholder="Rechercher...">
						</div>
					</div>
					<div class="form-group">
      					<label class="col-lg-2 control-label">Sélection :</label>
      					<div class="col-lg-10">
        					<div class="radio">
          						<label>
            						<input name="searchOpt" type="radio" id="optionsRadios1" value="group" checked>
            						Groupe
          						</label>
        					</div>
        					<div class="radio">
          						<label>
            						<input name="searchOpt" type="radio" id="optionsRadios2" value="person">
            						Personne
         						</label>
        					</div>
      					</div>
    				</div>
					<div class="form-group">
			    		<div class="col-lg-10 col-lg-offset-2">
			        		<button type="submit" class="btn btn-primary">Rechercher</button>
			      		</div>
			      	</div>
				</fieldset>
			</form>
			<hr />
			
			<!-- Annuaire -->
			<h3>Liste des groupes</h3>
			<table class="table table-hover table-striped">
				<thead>
   					<tr>
     					<th>Nom du groupe</th>
     				</tr>
     				</thead>
     				<tbody>
     					<c:forEach items="${groups}" var="group">
     						<tr>
     							<td><a href="${showPersonList}${group.id}"><c:out value="${group.name}" /></a></td>
     						</tr>
     					</c:forEach>
     				</tbody>
			</table>
		</div>
			
		<!-- Pied de page -->
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	</body>
</html>