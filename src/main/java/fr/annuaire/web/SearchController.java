package fr.annuaire.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.annuaire.business.DirectoryManager;
import fr.annuaire.model.Group;
import fr.annuaire.model.Person;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 *
 * Contrôleur de gestion de la recherche.
 */

@Controller
@RequestMapping("/recherche")
public class SearchController {

	@Autowired
	DirectoryManager manager;
	
	@Autowired
	@Qualifier("directoryValidator")
	DirectoryValidator validator;
	
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
	 * Renvoie à la vue les résulats de la recherche.
	 * 
	 * @param search
	 * 		Le contenu de la recherche.
	 * 
	 * @return La vue d'affichage des résultats de la recherche ou la vue d'affichage de l'annuaire (page d'accueil) si il y a une erreur.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView runSearch(@RequestParam(value = "search") String search, @RequestParam(value = "searchOpt") String option) {
		
		ModelAndView mv = new ModelAndView("redirect:/annuaire/accueil/");
		
		if (!search.trim().equals("") && validator.optionValid(option)) {
		
			mv = new ModelAndView("searchResult");
			
			if (option.equals("group")) {
				
				Collection<Group> groups = manager.searchGroup(search);
				
				if (groups.isEmpty())
					mv.addObject("message", "Aucun résultat pour cette recherche.");
				else
					mv.addObject("groups", groups);
				
				mv.addObject("option", "groupes");
			
			} else {
				
				Collection<Person> persons = manager.searchPerson(search);
				
				if (persons.isEmpty())
					mv.addObject("message", "Aucun résultat pour cette recherche.");
				else
					mv.addObject("persons", persons);
				
				mv.addObject("option", "personnes");
			}
			
			mv.addObject("search", search);	
		}
		
		return mv;
    }
}
