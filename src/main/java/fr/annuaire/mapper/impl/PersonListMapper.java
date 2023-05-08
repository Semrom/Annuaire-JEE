package fr.annuaire.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.annuaire.mapper.ResultSetToBean;
import fr.annuaire.model.Person;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 * 
 * Mapper pour le bean "Person" pour la recherche (pas tous les paramètres de la personne sont nécessaires).
 */
public class PersonListMapper implements ResultSetToBean<Person> {

	@Override
	public Person toBean(ResultSet rs) throws SQLException {
		
		Person p = new Person();
		
		p.setId(rs.getLong("identifiant_personne"));
		p.setLastname(rs.getString("nom_personne"));
		p.setFirstname(rs.getString("prenom_personne"));
		
		return p;
	}

}

