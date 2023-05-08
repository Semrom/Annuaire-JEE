package fr.annuaire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.annuaire.business.DirectoryManager;
import fr.annuaire.model.Person;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 *
 * Contrôleur de gestion de l'authentification.
 */

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	DirectoryManager manager;
	
	@Autowired
	@Qualifier("authenticationValidator")
	AuthenticationValidator validator;
	
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
	
	/**
	 * Crée une instance de "Person" pour l'authentification (attribut du modèle).
	 * 
	 * @param email
	 * 		L'email de la personne.
	 * @param password
	 * 		Le mot de passe de la personne.
	 * 
	 * @return L'instance de "Person" pour l'authentification.
	 */
    @ModelAttribute("person")
    public Person newPerson(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password) {
        
    	Person person = new Person();
    	person.setEmail(email);
    	person.setPassword(password);
    	
    	return person;
    }
	
	/**
	 * Affiche le formulaire d'authentification.
	 * 
	 * @return La vue du formulaire d'authentification si la personne n'est pas connectée. Sinon, la vue d'affichage de l'annuaire (page d'accueil).
	 */
	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public String showAuthForm() {
        
		if (validator.isConnected(user))
			return "redirect:/annuaire/accueil/";
		else
			return "authenticationForm";
    }
	
	/**
	 * Connecte une personne qui se trouve dans l'annuaire (après validation des données).
	 * 
	 * @param person
	 * 		La personne à connecter (email et mot de passe).
	 * @param result
	 * 		Le conteneur des éventuels erreurs lors de la validation des données.
	 * 
	 * @return La vue d'affichage de l'annuaire (la page d'accueil) si il n'y a pas d'erreur, sinon le formulaire d'authentification.
	 */
	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String loginPerson(@ModelAttribute Person person, BindingResult result) {
        
		validator.validate(person, result);

		if (result.hasErrors()) {

			return "authenticationForm";
		}
		
		Person p = manager.login(person);
		
		if(p != null) {
			
			user.setId(p.getId());
			user.setName(p.getLastname());
			user.setFirstname(p.getFirstname());
			
			return "redirect:/annuaire/accueil/";
		
		} else {
			
			/* On se sert du champ "password" pour stocker cette erreur personnalisée. */
			result.rejectValue("password", "error.connect", "Identifiants incorrects.");
			
			return "authenticationForm";
		}
    }
	
	/**
	 * Déconnecte une personne connecté.
	 * 
	 * @return La vue d'affichage de l'annuaire (page d'accueil).
	 */
	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public String logoutPerson() {
        
		if (validator.isConnected(user)) {
			user.setId(-1);
			user.setName(null);
			user.setFirstname(null);
		}
		
		return "redirect:/annuaire/accueil/";
    }
}
