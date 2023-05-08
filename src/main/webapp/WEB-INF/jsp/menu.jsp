<div class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/gestion_annuaire_jee/annuaire/accueil/">Gestion d'un annuaire (Projet JEE)</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    	<c:choose>
  			<c:when test="${user.name == null}">
		    	<ul class="nav navbar-nav navbar-right">
		        	<li><a href="/gestion_annuaire_jee/annuaire/auth/connexion">Connexion</a></li>
		      	</ul>
		    </c:when>
		    <c:otherwise>
		    	<ul class="nav navbar-nav navbar-right">
			    	<li class="dropdown">
			        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><c:out value="${user.name} ${user.firstname}" /> <span class="caret"></span></a>
			          	<ul class="dropdown-menu" role="menu">
			            	<li><a href="/gestion_annuaire_jee/annuaire/personne/profil/${user.id}">Mes informations</a></li>
			            	<li class="divider"></li>
			            	<li><a href="/gestion_annuaire_jee/annuaire/auth/deconnexion">Déconnexion</a></li>
			            </ul>
			        </li>
			    </ul>
		    </c:otherwise>
	    </c:choose>
    </div>
  </div>
</div>