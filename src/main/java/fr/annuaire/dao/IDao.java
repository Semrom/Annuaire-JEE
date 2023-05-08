package fr.annuaire.dao;

import java.util.Collection;

import fr.annuaire.dao.exceptions.DaoException;
import fr.annuaire.utils.DatabaseTools;

/**
 * @author Romain Semler - Marvin Vauge
 *
 * Interface de la DAO.
 */
public interface IDao<T> {
	
	/**
	 * Ajoute un objet T.
	 * 
	 * @param obj
	 * 		L'instance de l'objet
	 * 
	 * @return Le nombre de lignes manipulées par la requête.
	 * 
	 * @throws DaoException Si une erreur survient.
	 * 
	 * @see DatabaseTools#executeUpdate(String, Object...)
	 */
	int add(T obj) throws DaoException;
	
	/**
	 * Récupère toutes les instances créées d'un objet T.
	 * 
	 * @return Une collection contenant les instances de T qui existent.
	 * 
	 * @throws DaoException Si une erreur survient.
	 */
	Collection<T> findAll() throws DaoException;

	/**
	 * Récupère un objet T avec l'identifiant.
	 * 
	 * @param id
	 * 		L'identifiant de l'objet.
	 * 
	 * @return L'instance de l'objet à récupérer. Null si l'objet n'existe pas.
	 * 
	 * @throws DaoException Si une erreur survient.
	 */
	T find(long id) throws DaoException;

	/**
	 * Sauvegarde un objet T (modifié).
	 * 
	 * @param obj
	 * 		L'instance de l'objet.
	 * 
	 * @return Le nombre de lignes manipulées par la requête.
	 * 
	 * @throws DaoException Si une erreur survient.
	 * 
	 * @see DatabaseTools#executeUpdate(String, Object...)
	 */
	int update(T obj) throws DaoException;
	
	/**
	 * Supprime un objet T.
	 * 
	 * @param id
	 * 		L'identifiant de l'objet.
	 * 
	 * @throws DaoException Si une erreur survient.
	 * 
	 * @see DatabaseTools#executeUpdate(String, Object...)
	 */
	void remove(long id) throws DaoException;
	
	/**
	 * Récupère toutes les instances de T se rapprochant des termes d'une recherche.
	 * 
	 * @param search
	 * 		Les termes de la recherche.
	 * 
	 * @return Une collection contenant les instances de T résultant de la recherche.
	 * 
	 * @throws DaoException Si une erreur survient.
	 */
	Collection<T> searchBeans(String search) throws DaoException;
}
