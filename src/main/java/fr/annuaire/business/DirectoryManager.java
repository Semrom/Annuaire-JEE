package fr.annuaire.business;

import java.util.Collection;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.annuaire.dao.impl.GroupDao;
import fr.annuaire.dao.impl.PersonDao;
import fr.annuaire.model.Group;
import fr.annuaire.model.Person;

/**
 * @author Romain Semler - Marvin Vauge
 * @version 1.0
 *
 * Manager de l'annuaire (en contact avec la DAO).
 */

@Service
public class DirectoryManager implements IDirectoryManager {

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	public Person login(Person person) {

		Person p = (Person) personDao.findByEmail(person.getEmail());
		
		if (p != null && BCrypt.checkpw(person.getPassword(), p.getPassword())) {
			
			return p;
		
		} else {
			
			return null;
		}
	}

	@Override
	public Person findPerson(long personId) {
		
		return personDao.find(personId);
	}

	@Override
	public Group findGroup(long groupId) {
		
		return groupDao.find(groupId);
	}

	@Override
	public Collection<Group> findAllGroups() {
		
		return groupDao.findAll();
	}

	@Override
	public Collection<Person> findPersonsFromGroup(long groupId) {
		
		return groupDao.findPersons(groupId);
	}

	@Override
	public void savePerson(Person person) {
		
		personDao.update(person);
	}

	@Override
	public Collection<Group> searchGroup(String search) {
		
		return groupDao.searchBeans(search);
	}

	@Override
	public Collection<Person> searchPerson(String search) {
		
		return personDao.searchBeans(search);
	}
}
