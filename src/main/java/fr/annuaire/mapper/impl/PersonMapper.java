package fr.annuaire.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.annuaire.mapper.ResultSetToBean;
import fr.annuaire.model.Person;
import fr.annuaire.utils.DateTools;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 * 
 * Mapper pour le bean "Person".
 */

public class PersonMapper implements ResultSetToBean<Person> {
	
	@Override
	public Person toBean(ResultSet rs) throws SQLException {
		
		Person p = new Person();
		String date = null;

		if (rs.getDate("date_naissance_personne") != null) {
			DateTools ds = new DateTools();
			date = ds.convertToString(rs.getDate("date_naissance_personne"));
		}
		
		p.setId(rs.getLong("identifiant_personne"));
		p.setLastname(rs.getString("nom_personne"));
		p.setFirstname(rs.getString("prenom_personne"));
		p.setEmail(rs.getString("email_personne"));
		p.setWebsite(rs.getString("site_personne"));
		p.setBirthdate(date);
		p.setPassword(rs.getString("mdp_personne"));
		p.setGroupId(rs.getLong("identifiant_groupe"));
		
		return p;
	}
}
