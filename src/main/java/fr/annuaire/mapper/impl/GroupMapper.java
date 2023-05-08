package fr.annuaire.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.annuaire.mapper.ResultSetToBean;
import fr.annuaire.model.Group;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 * 
 * Mapper pour le bean "Group".
 */
public class GroupMapper implements ResultSetToBean<Group> {

	@Override
	public Group toBean(ResultSet rs) throws SQLException {
		
		Group g = new Group();
		
		g.setId(rs.getLong("identifiant_groupe"));
		g.setName(rs.getString("nom_groupe"));
		
		return g;
	}

}
