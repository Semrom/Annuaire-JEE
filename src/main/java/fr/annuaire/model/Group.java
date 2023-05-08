package fr.annuaire.model;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 * 
 * Bean groupe.
 */
public class Group {
	
	private long id;
	private String name;
	
	public Group() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
