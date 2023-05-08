package fr.annuaire.dao.exceptions;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.1
 * 
 * Exceptions de la DAO.
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(String message, Throwable cause) {
		
		super(message, cause);
	}
}
