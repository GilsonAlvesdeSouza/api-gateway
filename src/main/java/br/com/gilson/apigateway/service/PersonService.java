package br.com.gilson.apigateway.service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.gilson.apigateway.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public Person findById(String id) {

		logger.info("Find one person! id = " + id);

		Person person = new Person();

		person.setId(counter.incrementAndGet());
		person.setFirstName("Gilson");
		person.setLastName("Souza");
		person.setAddress("Anápolis-Go - Brasil");
		person.setGender("Male");

		return person;
	}
}
