package br.com.gilson.apigateway.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gilson.apigateway.dto.PersonDto;
import br.com.gilson.apigateway.model.PersonModel;
import br.com.gilson.apigateway.service.PersonService;
import jakarta.validation.Valid;

@RestController()
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping()
	public ResponseEntity<Object> create(@RequestBody @Valid PersonDto personDto) {

		var personModel = new PersonModel();

		BeanUtils.copyProperties(personDto, personModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personModel));
	}

	@GetMapping()
	public ResponseEntity<List<PersonModel>> findAll() {

		var persons = personService.findAll();

		return ResponseEntity.ok(persons);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonModel> getById(@PathVariable(value = "id") String id) throws Exception {

		var person = personService.findById(id);

		return ResponseEntity.ok(person);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonModel> update(@PathVariable(value = "id") String id,
			@RequestBody() PersonModel personBody) throws Exception {
		var person = personService.update(id, personBody);

		return ResponseEntity.ok(person);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) throws Exception {
		personService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
