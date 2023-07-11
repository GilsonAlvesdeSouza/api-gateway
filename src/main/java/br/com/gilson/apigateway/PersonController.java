package br.com.gilson.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gilson.apigateway.model.Person;
import br.com.gilson.apigateway.service.PersonService;

@RestController()
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable(value = "id") String id) throws Exception {

		var person = personService.findById(id);

		return ResponseEntity.ok(person);
	}

}
