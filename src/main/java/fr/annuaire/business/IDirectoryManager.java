package fr.annuaire.business;

import java.util.Collection;

import fr.annuaire.model.Group;
import fr.annuaire.model.Person;

/**
 * @author Romain Semler - Marvin Vauge
 *
 * Interface du manager.
 */
public interface IDirectoryManager {
	
	/**
	 * Vérifie qu'une personne a l'autorisation de se connecter.
	 * 
	 * @param person
	 * 		La personne tentant de se connecter.
	 * 
	 * @return Les informations de la personne si la personne est autorisée, null sinon.
	 */
	Person login(Person person);
	
	/**
	 * Récupère une personne de l'annuaire.
	 * 
	 * @param personId
	 * 		L'identifiant de la personne.
	 * 
	 * @return La personne récupérée.
	 */
	Person findPerson(long personId);
	
	/**
	 * Récupère un groupe de l'annuaire.
	 * 
	 * @param groupId
	 * 		L'identifiant du groupe
	 * 
	 * @return Le groupe récupéré.
	 */
	Group findGroup(long groupId);
	
	/**
	 * Récupère tous les groupes de l'annuaire.
	 * 
	 * @return Une collection contenant tous les groupes.
	 */
	Collection<Group> findAllGroups();
	
	/**
	 * Récupère toutes les personnes d'un groupe de l'annuaire.
	 * 
	 * @param groupId
	 * 		L'identifiant du groupe.
	 * 
	 * @return Une collection contenant toutes les personnes du groupe.
	 */
	Collection<Person> findPersonsFromGroup(long groupId);
	
	/**
	 * Met à jour une personne de l'annuaire.
	 * 
	 * @param person
	 * 		La personne à mettre à jour.
	 */
	void savePerson(Person person);
	
	/**
	 * Récupère tous les groupes se rapprochant des termes de la recherche.
	 * 
	 * @param search
	 * 		Les termes de la recherche.
	 * 
	 * @return Les groupes résultant de la recherche.
	 */
	Collection<Group> searchGroup(String search);
	
	/**
	 * Récupère toutes les personnes se rapprochant des termes de la recherche.
	 * 
	 * @param search
	 * 		Les termes de la recherche.
	 * 
	 * @return Les personnes résultant de la recherche.
	 */
	Collection<Person> searchPerson(String search);
}
