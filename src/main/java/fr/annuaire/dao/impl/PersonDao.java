package fr.annuaire.dao.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import fr.annuaire.dao.IDao;
import fr.annuaire.dao.exceptions.DaoException;
import fr.annuaire.mapper.impl.PersonListMapper;
import fr.annuaire.mapper.impl.PersonMapper;
import fr.annuaire.model.Person;
import fr.annuaire.utils.DatabaseTools;
import fr.annuaire.utils.DateTools;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 * 
 * DAO pour la personne.
 */
public class PersonDao extends DatabaseTools implements IDao<Person> {
	
	@Override
	public int add(Person p) throws DaoException {
		
		try {
			
			String req = "INSERT INTO personne " +
						 "(nom_personne, prenom_personne, email_personne, " +
					     "site_personne, date_naissance_personne, mdp_personne, identifiant_groupe) " +
						 "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			int manipulatedRows = this.executeUpdate(req, p.getLastname(), p.getFirstname(), p.getEmail(), p.getWebsite(), p.getBirthdate(), p.getPassword(), p.getGroupId());
						
			return manipulatedRows;
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}
	}
	
	@Override
	public Collection<Person> findAll() {
		
		try {
			
			return findBeans("SELECT * FROM personne", new PersonMapper());
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}
	}

	@Override
	public Person find(long id) {
		
		try {
			
			String req = "SELECT * FROM personne WHERE identifiant_personne = ?";
			
			return findBean(req, new PersonMapper(), id);
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}	
	}

	@Override
	public int update(Person p) {
		
		try {
			
			String req = "UPDATE personne " +
						 "SET nom_personne = ?, " +
						 "prenom_personne = ?, " +
						 "email_personne = ?, " +
						 "site_personne = ?, " +
						 "date_naissance_personne = ?," +
						 "mdp_personne = ? " +
						 "WHERE identifiant_personne = ?";
			
			Date date = null;
			
			if (p.getBirthdate() != null && !p.getBirthdate().equals("")) {
				
				DateTools ds = new DateTools();
				date = ds.convertToDate(p.getBirthdate());
			}
			
			String lastname = null;
			
			if (p.getLastname() != null)
				lastname = p.getLastname().toUpperCase();
			
			int manipulatedRows = this.executeUpdate(req, lastname, p.getFirstname(), p.getEmail(), p.getWebsite(), date, p.getPassword(), p.getId());
						
			return manipulatedRows;
			
		} catch (SQLException | ParseException e) {
			
			throw new DaoException("DAO Exception !", e);
		}	
	}

	@Override
	public void remove(long id) {
		
		try {
			
			String req = "DELETE FROM personne WHERE identifiant_personne = ?";
			
			this.executeUpdate(req, id);
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}
	}
	
	@Override
	public Collection<Person> searchBeans(String search) throws DaoException {
		
		try {
			
			String req = "SELECT identifiant_personne, nom_personne, prenom_personne FROM personne WHERE LOWER(nom_personne) LIKE LOWER(?) OR LOWER(prenom_personne) LIKE LOWER(?) OR LOWER(CONCAT(prenom_personne, ' ', nom_personne)) LIKE LOWER(?) ORDER BY nom_personne";
			
			return searchBeans(req, new PersonListMapper(), "%" + search + "%");
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}
	}
	
	/**
	 * Récupère une personne avec l'email.
	 * 
	 * @param email
	 * 		L'email de la personne.
	 * 
	 * @return Une instance de la personne ou null si la personne n'existe pas.
	 */
	public Person findByEmail(String email) {
		
		try {
			
			String req = "SELECT * FROM personne WHERE email_personne = ?";
			
			return findBean(req, new PersonMapper(), email);
			
		} catch (SQLException e) {
			
			throw new DaoException("DAO Exception !", e);
		}	
	}
}
