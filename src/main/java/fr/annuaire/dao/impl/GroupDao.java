package fr.annuaire.dao.impl;

import java.sql.SQLException;
import java.util.Collection;

import fr.annuaire.dao.IDao;
import fr.annuaire.dao.exceptions.DaoException;
import fr.annuaire.mapper.impl.GroupMapper;
import fr.annuaire.mapper.impl.PersonListMapper;
import fr.annuaire.model.Group;
import fr.annuaire.model.Person;
import fr.annuaire.utils.DatabaseTools;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 * 
 * DAO pour le groupe.
 */
public class GroupDao extends DatabaseTools implements IDao<Group> {

	@Override
	public int add(Group g) throws DaoException {
		
		try {
			
			String req = "INSERT INTO groupe (nom_groupe) VALUES (?)";
			
			int manipulatedRows = this.executeUpdate(req, g.getName());
			
			return manipulatedRows;
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		
		}		
	}
	
	@Override
	public Collection<Group> findAll() {
		
		try {
			
			return findBeans("SELECT * FROM groupe", new GroupMapper());
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}
	}

	@Override
	public Group find(long id) {
			
		String req = "SELECT * FROM groupe WHERE identifiant_groupe = ? ORDER BY nom_groupe";
		
		try {
			
			return findBean(req, new GroupMapper(), id);
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}			
	}

	@Override
	public int update(Group g) {
		
		try {
			
			String req = "UPDATE groupe " +
						 "SET nom_groupe = ? " +
						 "WHERE identifiant_groupe = ?";
			
			int manipulatedRows = this.executeUpdate(req, g.getName(), g.getId());
			
			return manipulatedRows;
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}	
		
	}

	@Override
	public void remove(long id) {
		
		try {
			
			String req = "DELETE FROM groupe WHERE identifiant_groupe = ?";
			
			this.executeUpdate(req, id);
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}
	}
	
	@Override
	public Collection<Group> searchBeans(String search) throws DaoException {
		
		try {
			
			String req = "SELECT * FROM groupe WHERE LOWER(nom_groupe) LIKE LOWER(?) ORDER BY nom_groupe";
			
			return searchBeans(req, new GroupMapper(), search);
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}
	}
	
	/**
	 * Récupère toutes les instances des personnes contenues dans un groupe.
	 * 
	 * @param id
	 * 		L'identifiant du groupe.
	 * 
	 * @return Une colletion contenant les instances des personnes appartenant au groupe.
	 */
	public Collection<Person> findPersons(long id) {
		
		String req = "SELECT identifiant_personne, nom_personne, prenom_personne FROM personne WHERE identifiant_groupe = " + id + " ORDER BY nom_personne";
	
		try {
			
			return findBeans(req, new PersonListMapper());
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}
	}
}
