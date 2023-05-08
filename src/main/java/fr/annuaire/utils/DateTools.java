package fr.annuaire.utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 *
 * Service en charge du formatage des dates.
 */
public class DateTools {

	/**
	 * Convertit une date en chaîne de caractères dans le format DD/MM/YYYY.
	 * 
	 * @param date
	 * 		La date à convertir.
	 * 
	 * @return Une chaîne de caractères contenant la date dans le format DD/MM/YYYY.
	 */
	public String convertToString(java.sql.Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.applyPattern("dd/MM/yyyy");

		return sdf.format(date);
	}
	
	/**
	 * Convertit une chaîne de caractères en date dans le format YYYY-MM-DD.
	 * 
	 * @param date
	 * 		La chaîne de caractères contenant la date.
	 * 
	 * @return La date dans le format YYYY-MM-DD.
	 * 
	 * @throws ParseException Si une erreur survient lors de la conversion du type "String" vers le type "Date".
	 */
	public Date convertToDate(String date) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oldDate = sdf.parse(date);
		sdf.applyPattern("yyyy-MM-dd");
		String newDate = sdf.format(oldDate);

		return new java.sql.Date(sdf.parse(newDate).getTime());
	}
}
