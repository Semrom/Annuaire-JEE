package fr.annuaire.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/errors")
public class ErrorController {

	@Autowired
    User user;
	
	/**
	 * Récupère l'utilisateur en session (attribut du modèle).
	 * 
	 * @return L'utilisateur en session.
	 */
	@ModelAttribute("user")
    public User getUser() {
        return user;
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

		if (httpRequest == null)
			return new ModelAndView("redirect:/annuaire/accueil/");
			
		ModelAndView errorPage = new ModelAndView("errorPage");
		
		int errorCode = (int) httpRequest.getAttribute("javax.servlet.error.status_code");
		String errorMessage = (String) httpRequest.getAttribute("javax.servlet.error.exception");
		String infoMessage = "";
		
		switch (errorCode) {
		
			case 400:
				infoMessage = "Erreur 400 : La syntaxe de la requête est érronée.";
				break;
  
			case 401:
				infoMessage = "Erreur 401 : Authentification nécessaire.";
				break;
				
			case 403:
				infoMessage = "Erreur 403 : Accès à la ressource interdit.";
				break;
				
			case 404:
				infoMessage = "Erreur 404 : Page introuvable.";
				break;
				
			case 500:
				infoMessage = "Erreur 500 : Erreur interne du serveur.";
				break;
				
			default:
				return new ModelAndView("redirect:/annuaire/accueil/");
		}
		
		errorPage.addObject("errorCode", errorCode);
		errorPage.addObject("infoMessage", infoMessage);
		errorPage.addObject("errorMessage", errorMessage);
		
		return errorPage;
	}
}
