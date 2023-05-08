package fr.annuaire.web;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
 * Contrôleur de gestion des personnes.
 */

@Controller
@RequestMapping("/personne")
public class PersonController {

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
	 * Récupère les informations de la personne connectée
	 * en vue de la modification de son profil (attribut du modèle).
	 * 
	 * @param id
	 * 		L'identifiant de la personne connectée.
	 * 
	 * @return Une instance de la personne connectée, null si l'identifiant est incorrect.
	 */
	@ModelAttribute
	public Person getPerson(@RequestParam(value = "profil", required = false) Long id) {
	    
		if (id != null) {
		
			if (validator.idValid(id) && id == user.getId()) {
				
				return manager.findPerson(id);
			}
		}
		
		return null;
	}
	
	/**
	 * Renvoie à la vue les informations d'une personne de l'annuaire.
	 * 
	 * @param idPerson
	 * 		L'identifiant de la personne.
	 * 
	 * @return La vue d'affichage du profil d'une personne ou la vue d'affichage de l'annuaire (page d'accueil) si la personne n'existe pas.
	 */
	@RequestMapping(value = "/profil/{idPerson}", method = RequestMethod.GET)
    public ModelAndView showPerson(@PathVariable long idPerson) {
		
		if (validator.idValid(idPerson)) {
			
			Person p = manager.findPerson(idPerson);
			
			if (p == null)
				return new ModelAndView("redirect:/annuaire/accueil/");
			
			Group g = manager.findGroup(p.getGroupId());
			
			if (p.getWebsite() == null)
				p.setWebsite("Aucun");
			
			ModelAndView mv =  new ModelAndView("person");
			
			mv.addObject("groupName", g.getName());
			mv.addObject("person", p);
			
			return mv;
		
		} else {
			
			return new ModelAndView("redirect:/annuaire/accueil/");
		}		
    }
	
	/**
	 * Accès au formulaire de mise à jour des informations de la personne connectée.
	 * 
	 * @param p
	 * 		La personne connectée.
	 * 
	 * @return La vue d'affichage du formulaire de modification ou la vue d'affichage de l'annuaire (page d'accueil) si la personne n'existe pas.
	 * 
	 * @throws ParseException Si une erreur survient lors de la conversion du type "String" vers le type "Date".
	 */
	@RequestMapping(value = "/modifier", method = RequestMethod.GET)
    public String editPerson(@ModelAttribute Person p) throws ParseException {
		
		if (p == null)
			return "redirect:/annuaire/accueil/";	
		
		return "updatePersonForm";
    }
	
	/**
	 * Enregistre une personne qui a été modifiée.
	 * 
	 * @param p
	 * 		La personne modifiée.
	 * @param result
	 * 		Le conteneur des éventuels erreurs lors de la validation des données.
	 * 
	 * @return La vue d'affichage du profil de la personne ou la vue du formulaire de modification en cas d'erreurs.
	 * 
	 * @throws ParseException Si une erreur survient lors de la conversion du type "String" vers le type "Date".
	 */
	@RequestMapping(value = "/modifier", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute Person p, BindingResult result) throws ParseException {
		
		validator.validate(p, result);

		if (result.hasErrors()) {
			return "updatePersonForm";
		}

		manager.savePerson(p);
		
		user.setName(p.getLastname());
		user.setFirstname(p.getFirstname());
		
		return "redirect:/annuaire/personne/profil/" + user.getId();	
    }
}
