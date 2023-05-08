package fr.annuaire.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.annuaire.business.DirectoryManager;
import fr.annuaire.model.Group;
import fr.annuaire.model.Person;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 *
 * Contrôleur de gestion des listes de groupes et de personnes.
 */

@Controller
@RequestMapping("/accueil")
public class ListController {
	
	@Autowired
	DirectoryManager manager;
	
	@Autowired
	@Qualifier("personValidator")
	PersonValidator validator;
	
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
	 * Créer la liste des groupes (attribut du modèle).
	 * 
	 * @return Une collection contenant tous les groupes présents dans l'annuaire.
	 */
	@ModelAttribute("groups")
	Collection<Group> groups() {

	    return manager.findAllGroups();
	}
	
	/**
	 * Affiche une liste de tous les groupes présents dans l'annuaire
	 * (page d'acccueil de l'application).
	 * 
	 * @return La vue d'affichage de la liste des groupes (la page d'accueil de l'annuaire).
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String listGroups() {

		return "home";
    }
	
	/**
	 * Renvoie à la vue une liste de toutes les personnes contenues dans le groupe courant.
	 * 
	 * @param idGroup
	 * 		L'indentifiant du groupe courant.
	 * 
	 * @return La vue d'affichage des personnes appartenant au groupe ou la vue d'affichage de l'annuaire (page d'accueil) si le groupe n'existe pas.
	 */
	@RequestMapping(value = "/groupe/{idGroup}", method = RequestMethod.GET)
    public ModelAndView personsFromGroup(@PathVariable long idGroup) {
		
		if (validator.idValid(idGroup)) {
			
			Collection<Person> persons = manager.findPersonsFromGroup(idGroup);
			
			Group g = manager.findGroup(idGroup);
			
			if (g == null)
				return new ModelAndView("redirect:/annuaire/accueil/");
			
			ModelAndView mv =  new ModelAndView("personList");
			mv.addObject("groupName", g.getName());
			mv.addObject("persons", persons);
			
			return mv;
		
		} else {
			
			return new ModelAndView("redirect:/annuaire/accueil/");
		}
    }
}
