package fr.annuaire.mapper;

import java.sql.SQLException;

public interface ResultSetToBean<T> {

	/**
	 * Retourne un bean contenant les données contenues dans le set de résultats.
	 * 
	 * @param rs
	 * 		Le set de résultat.
	 * 
	 * @return Le bean crée à partir des données du set.
	 * 
	 * @throws SQLException Si une erreur survient lors de la lecture du set de résultat.
	 */
	 T toBean(java.sql.ResultSet rs) throws SQLException;
}
