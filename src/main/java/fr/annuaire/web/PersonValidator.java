package fr.annuaire.web;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.annuaire.model.Person;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 *
 * Service en charge de la vérification et de la validation
 * des personnes de l'annuaire.
 */

@Service
public class PersonValidator extends DirectoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Person.class.isAssignableFrom(clazz);
	}
	
	/**
	 * Vérifie si une personne est valide (tous ses attributs sont corrects).
	 * 
	 * @param target
	 * 		L'objet à vérifier (ici "Person").
	 * @param errors
	 * 		Le conteneur d'erreur (se remplit si des erreurs surviennent).
	 */
	@Override
	public void validate(Object target, Errors errors) {
		
		Person p = (Person) target;
		
		/* Champs obligatoires (tout sauf site web). */
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "person.lastname");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "person.firstname");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "person.email");
        
        
        /*
         * On vérifie le format de l'email (à condition qu'il ne soit pas vide).
         */
        
        if (!emailValid(p.getEmail()) && !p.getEmail().isEmpty()) {
        	errors.rejectValue("email", "person.email.format");
        }
        
        /*
         * On vérifie le format de la date de naissance (à condition qu'elle ne soit pas vide).
         */
        
        if (!p.getBirthdate().matches("\\d{2}/\\d{2}/\\d{4}") && !p.getBirthdate().isEmpty()) {
        	errors.rejectValue("birthdate", "person.birthdate.format");
        }
        
        /* 
         * Champ facultatif (site web).
         * On vérifie si il finit bien par ".extension", 
         * qu'il ne commence pas par "http" ou "https" 
         * et qu'il ne contient aucune ponctuation à part le point et le tiret.
         */
        
        if (p.getWebsite().trim().length() > 0) {
        	
        	if (p.getWebsite().matches("^(http|https):\\/\\/.+") || 
        		p.getWebsite().matches(".*[,\\/#!$%\\^&\\*;:{}=_`~()].*") ||
        		!p.getWebsite().matches(".*\\.[a-z]{2,}$")) {
        		errors.rejectValue("website", "person.website.format");
        	}
        }
	}
}
