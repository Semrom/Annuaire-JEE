package fr.annuaire.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.annuaire.mapper.ResultSetToBean;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.5
 * 
 * Classe contenant les commandes d'exécution des différentes requêtes SQL de la DAO.
 * Réalisée avec des injections de dépendances par Spring.
 */
@Component
public class DatabaseTools {

	@Autowired
	private BasicDataSource basicDataSource;

	/**
	 * Récupère la DataSource courante.
	 * 
	 * @return La DataSource courante.
	 */
	public BasicDataSource getBasicDataSource() {
		
		return this.basicDataSource;
	}
	
	/**
	 * Modifie la DataSource.
	 * 
	 * @param bds
	 * 		La nouvelle DataSource.
	 */
	public void setBasicDataSource(BasicDataSource bds) {
		
		this.basicDataSource = bds;
	}

	/**
	 * Etablit une nouvelle connexion au serveur avec la DataSource.
	 * 
	 * @return L'instance de connexion.
	 * 
	 * @throws SQLException Si une erreur survient lors de la connexion.
	 */
	public Connection newConnection() throws SQLException {
		
		Connection conn = this.basicDataSource.getConnection();
	    return conn;
	}
	
	/**
	 * Ferme la connexion courante.
	 * 
	 * @param c
	 * 		La connexion courante.
	 * 
	 * @throws SQLException Si une erreur survient lors de la fermeture.
	 */
	public void close(Connection c) throws SQLException {
		
		if (c != null) c.close();
	}
	
	/**
	 * Exécute une simple requête SQL.
	 * 
	 * @param req
	 * 		La requête SQL à exécuter.
	 * 
	 * @return Le nombre de lignes manipulées par la requête, 0 si la requête ne retourne rien.
	 * 
	 * @throws SQLException Si une erreur SQL survient.
	 */
	public int execute(String req) throws SQLException {
		
		Connection co = null;
		
		try {
			co = newConnection();
			
			Statement stmt = co.createStatement();
			return stmt.executeUpdate(req);
			
		} finally {
			close(co);
		}
	}
	
	/**
	 * Exécute une requête SQL préparée destinée à modifier la base.
	 * 
	 * @param query
	 * 		La requête SQL préparée à exécuter.
	 * @param parameters
	 * 		Les éventuels paramètres qui complètent la requête.
	 * 
	 * @return Le nombre de lignes manipulées par la requête, 0 si la requête ne retourne rien.
	 * 
	 * @throws SQLException Si une erreur SQL survient.
	 */
	public int executeUpdate(String query, Object... parameters) throws SQLException {
		
		Connection co = null;
		
		try {
			
			co = newConnection();
			
			PreparedStatement st = co.prepareStatement(query);
			for (int i = 0; i < parameters.length; i++) {
			   
				if (parameters[i] instanceof Integer)
					st.setInt(i + 1, (int) parameters[i]);
				else if (parameters[i] instanceof String)
					st.setString(i + 1, (String) parameters[i]);
				else if (parameters[i] instanceof Long)
					st.setLong(i + 1, (Long) parameters[i]);
				else
					st.setDate(i + 1, (Date) parameters[i]);
			}

			return st.executeUpdate();
			
		} finally {
			close(co);
		}
	}	
	
	/**
	 * Récupère une instance créée d'un bean.
	 * 
	 * @param req
	 * 		La requête SQL préparée à exécuter.
	 * @param mapper
	 * 		Le mapper pour traiter le résultat.
	 * @param parameters
	 * 		Les éventuels paramètres qui complètent la requête.
	 * 
	 * @return L'instance du bean créé. Null si le bean n'existe pas.
	 * 
	 * @throws SQLException Si une erreur SQL survient.
	 */
	public <T> T findBean(String req, ResultSetToBean<T> mapper, Object... parameters) throws SQLException {
		
		Connection co = null;
		
		try {
			co = newConnection();
			
			PreparedStatement stmt = co.prepareStatement(req);
			
			for (int i = 0; i < parameters.length; i++) {
				   
				if (parameters[i] instanceof Integer)
					stmt.setInt(i + 1, (int) parameters[i]);
				else if (parameters[i] instanceof String)
					stmt.setString(i + 1, (String) parameters[i]);
				else if (parameters[i] instanceof Long)
					stmt.setLong(i + 1, (Long) parameters[i]);
				else
					stmt.setDate(i + 1, (Date) parameters[i]);
			}

			ResultSet rs = stmt.executeQuery();
			T obj = null;
			
			if(rs.first()) {
				
				obj =  mapper.toBean(rs);
			}
			
			return obj;
			
		} finally {
			close(co);
		}
	}
	
	/**
	 * Récupère toutes les instances créées d'un bean.
	 * 
	 * @param req
	 * 		La requête SQL à exécuter.
	 * @param mapper
	 * 		Le mapper pour traiter le résultat.
	 * 
	 * @return La collection contenant toutes les instances créées d'un bean.
	 * 
	 * @throws SQLException Si une erreur SQL survient.
	 */
	public <T> Collection<T> findBeans(String req, ResultSetToBean<T> mapper) throws SQLException {
		
		Connection co = null;
		
		try {
			co = newConnection();
			
			Statement stmt = co.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			Collection<T> beans = new ArrayList<T>();
				
			while (rs.next()) {
				
				beans.add(mapper.toBean(rs));
			}
			
			return beans;
			
		} finally {
			close(co);
		}
	}
	
	/**
	 * Récupère toutes les instances d'un bean correpsondant au filtre de recherche.
	 * 
	 * @param req
	 * 		La requête SQL préparée à exécuter.
	 * @param mapper
	 * 		Le mapper pour traiter le résultat.
	 * @param filter
	 * 		Le filtre de recherche.
	 * 
	 * @return La collection contenant toutes les instances d'un bean résultant de la recherche.
	 * 
	 * @throws SQLException Si une erreur SQL survient.
	 */
	public <T> Collection<T> searchBeans(String req, ResultSetToBean<T> mapper, String filter) throws SQLException {
		
		Connection co = null;
		
		try {
			co = newConnection();
			
			PreparedStatement stmt = co.prepareStatement(req);
			
			ParameterMetaData pmd = stmt.getParameterMetaData();
			
			if (pmd.getParameterCount() > 1) {
			
				stmt.setString(1, "%" + filter + "%");
				stmt.setString(2, "%" + filter + "%");
				stmt.setString(3, "%" + filter + "%");
			
			} else {
				
				stmt.setString(1, "%" + filter + "%");
			}

			
			ResultSet rs = stmt.executeQuery();
			Collection<T> beans = new ArrayList<T>();
				
			while (rs.next()) {
				
				beans.add(mapper.toBean(rs));
			}
			
			return beans;
			
		} finally {
			close(co);
		}
	}
}
