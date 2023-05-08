<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="home" value="/annuaire/accueil/" />

<!DOCTYPE html>
<html>
	<head>
		<title>Annuaire | Connexion</title>
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
	            		<h3>Connexion</h3>
	          		</div>
	        	</div>
	      	</div>
	      	
	      	<!-- Formulaire de connexion -->
	      	<form:form method="POST" commandName="person" cssClass="form-horizontal">
  				<fieldset>
    				<div class="form-group">
    					<label for="email" class="col-lg-2 control-label">Email *</label>
      					<div class="col-lg-10">
        					<form:input cssClass="form-control" maxlength="255" path="email" />
        					<form:errors path="email" cssClass="erreur" element="span" />
      					</div>
    				</div>
    				<div class="form-group">
    					<label for="password" class="col-lg-2 control-label">Mot de passe *</label>
      					<div class="col-lg-10">
        					<form:password cssClass="form-control" maxlength="255" path="password" />
        					<form:errors path="password" cssClass="erreur" element="span" />
      					</div>
    				</div>
    				<div class="form-group">
				    	<div class="col-lg-8 col-lg-offset-2">
				    		<p><a href="#" class="btn btn-primary btn-xs disabled">Mot de passe oublié ?</a></p>
				    		<p>* Champs obligatoires</p>
				    		<a class="btn btn-info" href="${home}">Retour</a>
				        	<button type="submit" class="btn btn-primary">Connexion</button>
				      	</div>
   					</div>
  				</fieldset>
			</form:form>
		</div>
		
		<!-- Pied de page -->
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	</body>
</html>