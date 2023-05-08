package fr.annuaire.web;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 *
 * Service en charge de la vérification et de la validation
 * des données générales de l'annuaire.
 */

@Service
public class DirectoryValidator {
	
	/**
	 * Vérifie si une personne est bien connectée.
	 * 
	 * @param user
	 * 		L'utilisateur de session.
	 * 
	 * @return true si la personne est bien connectée, false sinon.
	 */
	public boolean isConnected(User user) {
		
		return user.getName() != null;
	}
	
	/**
	 * Vérifie qu'un identifiant est bien supérieur à 0.
	 * 
	 * @param id
	 * 		Un identifiant.
	 * 
	 * @return true si l'identifiant est valide, false sinon.
	 */
	public boolean idValid(long id) {
		
		return id > 0;
	}
	
	/**
	 * Vérifie si une adresse mail est valide.
	 * 
	 * @param email
	 * 		L'adresse mail.
	 * 
	 * @return true si l'email est valide, false sinon.
	 */
	public boolean emailValid(String email) {
		
		EmailValidator emailValidator = EmailValidator.getInstance();
        
        return emailValidator.isValid(email);
	}
	
	/**
	 * Vérifie que l'option de recherche est bien égal à "group" ou à "person".
	 * 
	 * @param option
	 * 		L'option de recherche.
	 * 
	 * @return true si l'option est valide, false sinon.
	 */
	public boolean optionValid(String option) {
		
		return option.equals("group") || option.equals("person");
	}
}
