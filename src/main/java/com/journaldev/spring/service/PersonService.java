package com.journaldev.spring.service;

import com.journaldev.spring.model.Person;

import java.util.List;

public interface PersonService {

	void addPerson(Person p);
	void updatePerson(Person p);
	List<Person> listPersons();
	Person getPersonById(int id);
	Person getPersonWithAddressesById(int id);
	void removePerson(int id);

}
