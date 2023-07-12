package br.com.gilson.apigateway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gilson.apigateway.exceptions.BadRequestException;
import br.com.gilson.apigateway.model.PersonModel;
import br.com.gilson.apigateway.repository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

//	private final AtomicLong counter = new AtomicLong(); // serve para gerar números aleatórios
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Transactional
	public PersonModel save(PersonModel person) {
		logger.info("Creating person");

		var newPerson = personRepository.save(person);

		return newPerson;
	}

	public List<PersonModel> findAll() {
		logger.info("listing persons!");
		List<PersonModel> persons = new ArrayList<>();

		persons = personRepository.findAll();

		return persons;
	}

	public Optional<PersonModel> findById(String id) {

		logger.info("Find one person! id = " + id);

		if (!isNumber(id)) {
			throw new BadRequestException("informed id must be of numeric type");
		}

		return personRepository.findById(Long.parseLong(id));
	}

	@Transactional
	public void delete(PersonModel personModel) {

		logger.info("excluding person");

		personRepository.delete(personModel);
	}

	private boolean isNumber(String id) {

		return id.matches("[+]?[0-9]*");
	}

}
