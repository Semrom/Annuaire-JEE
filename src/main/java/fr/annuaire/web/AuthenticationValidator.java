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
 * des données de l'authentification.
 */

@Service
public class AuthenticationValidator extends DirectoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Person.class.isAssignableFrom(clazz);
	}

	/**
	 * Vérifie si les données d'une authentification sont correctes.
	 * 
	 * @param person
	 * 		La personne voulant se connecter.
	 * @param errors
	 * 		Le conteneur d'erreurs (se remplit si des erreurs surviennent).
	 */
	@Override
	public void validate(Object person, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "person.email");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "person.password");
		
	}
}
