package br.com.gilson.apigateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gilson.apigateway.model.Person;
import br.com.gilson.apigateway.service.PersonService;

@RestController()
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping()
	public ResponseEntity<List<Person>> findAll() {

		var persons = personService.findAll();

		return ResponseEntity.ok(persons);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable(value = "id") String id) throws Exception {

		var person = personService.findById(id);

		return ResponseEntity.ok(person);
	}

	@PostMapping()
	public ResponseEntity<Person> create(@RequestBody() Person personBody) {
		var person = personService.create(personBody);

		return ResponseEntity.ok(person);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable(value = "id") String id, @RequestBody() Person personBody) {
		var person = personService.update(id, personBody);

		return ResponseEntity.ok(person);
	}

}
