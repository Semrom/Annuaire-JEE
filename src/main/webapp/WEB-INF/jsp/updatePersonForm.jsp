<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="back" value="/annuaire/personne/profil/" />

<!DOCTYPE html>
<html>
	<head>
		<title>Annuaire | Modifier le profil</title>
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
	            		<h3>Modification du profil de <c:out value="${person.firstname} ${person.lastname}" /></h3>
	          		</div>
	        	</div>
	      	</div>
	      	
	      	<!-- Formulaire de connexion -->
	      	<form:form method="POST" commandName="person" cssClass="form-horizontal">
  				<fieldset>
    				<div class="form-group">
    					<label for="lastname" class="col-lg-2 control-label">Nom *</label>
      					<div class="col-lg-10">
        					<form:input cssClass="form-control" maxlength="255" path="lastname" />
        					<form:errors path="lastname" cssClass="erreur" element="span" />
      					</div>
    				</div>
    				<div class="form-group">
    					<label for="firstname" class="col-lg-2 control-label">Prénom *</label>
      					<div class="col-lg-10">
        					<form:input cssClass="form-control" maxlength="255" path="firstname" />
        					<form:errors path="firstname" cssClass="erreur" element="span" />
      					</div>
    				</div>
    				<div class="form-group">
    					<label for="email" class="col-lg-2 control-label">Email *</label>
      					<div class="col-lg-10">
        					<form:input cssClass="form-control" maxlength="255" path="email" />
        					<form:errors path="email" cssClass="erreur" element="span" />
      					</div>
    				</div>
    				<div class="form-group">
    					<label for="website" class="col-lg-2 control-label">Site web (http://)</label>
      					<div class="col-lg-10">
        					<form:input cssClass="form-control" maxlength="255" path="website" placeholder="votresite.fr (pas de \"http://\")" />
        					<form:errors path="website" cssClass="erreur" element="span" />
      					</div>
    				</div>
    				<div class="form-group">
    					<label for="birthdate" class="col-lg-2 control-label">Date de naissance</label>
      					<div class="col-lg-10">
        					<form:input cssClass="form-control" path="birthdate" placeholder="JJ/MM/AAAA" />
        					<form:errors path="birthdate" cssClass="erreur" element="span" />
      					</div>
    				</div>
    				<div class="form-group">
				    	<div class="col-lg-10 col-lg-offset-2">
				    		<p>* Champs obligatoires</p>
				    		<a class="btn btn-info" href="${back}${person.id}">Retour au profil</a>
				        	<button type="submit" class="btn btn-primary">Modifier</button>
				      	</div>
   					</div>
  				</fieldset>
			</form:form>
		</div>
		
		<!-- Pied de page -->
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	</body>
</html>