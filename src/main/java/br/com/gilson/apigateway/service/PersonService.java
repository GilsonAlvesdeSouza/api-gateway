package br.com.gilson.apigateway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.gilson.apigateway.exceptions.BadRequestException;
import br.com.gilson.apigateway.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public Person create(Person person) {
		logger.info("Creating person");

		Person newPerson = person;
		newPerson.setId(counter.incrementAndGet());

		return newPerson;
	}

	public List<Person> findAll() {
		logger.info("listing persons!");
		List<Person> persons = new ArrayList<>();

		for (int i = 1; i <= 9; i++) {
			Person person = MockPerson(i);
			persons.add(person);
		}

		return persons;
	}

	public Person findById(String id) {

		logger.info("Find one person! id = " + id);

		if (!isNumber(id)) {
			throw new BadRequestException("informed id must be of numeric type");
		}

		Person person = new Person();

		person.setId(Long.parseLong(id));
		person.setFirstName("Gilson");
		person.setLastName("Souza");
		person.setAddress("Anápolis-Go - Brasil");
		person.setGender("Male");

		return person;
	}

	public Person update(String id, Person person) {

		logger.info("updating person!");

		Person newPerson = person;
		if (!isNumber(id)) {
			throw new BadRequestException("informed id must be of numeric type");
		}
		person.setId(Long.parseLong(id));

		return newPerson;
	}

	public void delete(String id) {

		logger.info("excluding person");

		if (!isNumber(id)) {
			throw new BadRequestException("informed id must be of numeric type");
		}
	}

	private boolean isNumber(String id) {

		return id.matches("[+]?[0-9]*");
	}

	private Person MockPerson(int i) {

		Person person = new Person();

		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Person lastName " + i);
		person.setAddress("Person address " + i);
		person.setGender("Person gender " + i);
		return person;
	}
}
