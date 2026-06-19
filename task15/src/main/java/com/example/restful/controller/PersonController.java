package com.example.restful.controller;

import com.example.restful.dto.Person;
import com.example.restful.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonRepository personRepository;

	public PersonController(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@GetMapping
	public Iterable<Person> getAll() {
		return personRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable int id) {
		return personRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Person> create(@RequestBody Person person) {
		Person saved = personRepository.save(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable int id, @RequestBody Person person) {
		if (!personRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		person.setId(id);
		return ResponseEntity.ok(personRepository.save(person));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		if (!personRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		personRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
