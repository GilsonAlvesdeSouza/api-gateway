package br.com.gilson.apigateway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

		return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personModel));
	}

	@GetMapping()
	public ResponseEntity<List<PersonModel>> findAll() {

		return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value = "id") String id) throws Exception {

		Optional<PersonModel> personModelOptional = personService.findById(id);

		if (!personModelOptional.isPresent()) {
			Map<String, Object> messageResponse = errorMessageResponse("Person not found!", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageResponse);
		}

		return ResponseEntity.status(HttpStatus.OK).body(personModelOptional.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") String id, @RequestBody @Valid PersonDto personDto)
			throws Exception {
		Optional<PersonModel> personModelOptional = personService.findById(id);

		if (!personModelOptional.isPresent()) {
			Map<String, Object> messageResponse = errorMessageResponse("Person not found!", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageResponse);
		}

		var personModel = new PersonModel();

		BeanUtils.copyProperties(personDto, personModel);

		personModel.setId(personModelOptional.get().getId());

		return ResponseEntity.status(HttpStatus.OK).body(personService.save(personModel));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") String id) throws Exception {
		Optional<PersonModel> personModelOptional = personService.findById(id);

		if (!personModelOptional.isPresent()) {
			Map<String, Object> messageResponse = errorMessageResponse("Person not found!", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageResponse);
		}

		personService.delete(personModelOptional.get());

		Map<String, Object> messageResponse = errorMessageResponse("Person deletd sucessfyly!", HttpStatus.OK);

		return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
	}

	private Map<String, Object> errorMessageResponse(String message, Object code) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("code", "" + code);
		errorResponse.put("message", message);

		return errorResponse;
	}

}
