package br.com.gilson.apigateway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.gilson.apigateway.exceptions.BadRequestException;
import br.com.gilson.apigateway.model.PersonModel;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public PersonModel create(PersonModel person) {
		logger.info("Creating person");

		PersonModel newPerson = person;
		newPerson.setId(counter.incrementAndGet());

		return newPerson;
	}

	public List<PersonModel> findAll() {
		logger.info("listing persons!");
		List<PersonModel> persons = new ArrayList<>();

		for (int i = 1; i <= 9; i++) {
			PersonModel person = MockPerson(i);
			persons.add(person);
		}

		return persons;
	}

	public PersonModel findById(String id) {

		logger.info("Find one person! id = " + id);

		if (!isNumber(id)) {
			throw new BadRequestException("informed id must be of numeric type");
		}

		PersonModel person = new PersonModel();

		person.setId(Long.parseLong(id));
		person.setFirstName("Gilson");
		person.setLastName("Souza");
		person.setAddress("Anápolis-Go - Brasil");
		person.setGender("Male");

		return person;
	}

	public PersonModel update(String id, PersonModel person) {

		logger.info("updating person!");

		PersonModel newPerson = person;
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

	private PersonModel MockPerson(int i) {

		PersonModel person = new PersonModel();

		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Person lastName " + i);
		person.setAddress("Person address " + i);
		person.setGender("Person gender " + i);
		return person;
	}
}
