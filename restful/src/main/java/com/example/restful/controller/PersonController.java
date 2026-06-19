package com.example.restful.controller;

import com.example.restful.dto.Person;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final List<Person> persons = new ArrayList<>();
	private int nextId = 1;

	@GetMapping
	public List<Person> getAll() {
		return persons;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable int id) {
		return findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Person> create(@RequestBody Person person) {
		person.setId(nextId++);
		persons.add(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable int id, @RequestBody Person person) {
		Optional<Person> existing = findById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		person.setId(id);
		persons.set(persons.indexOf(existing.get()), person);
		return ResponseEntity.ok(person);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		Optional<Person> existing = findById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		persons.remove(existing.get());
		return ResponseEntity.noContent().build();
	}

	private Optional<Person> findById(int id) {
		return persons.stream()
				.filter(person -> person.getId() == id)
				.findFirst();
	}
}
