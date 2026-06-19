package com.example.restful.controller;

import com.example.restful.dto.Message;
import com.example.restful.dto.Person;
import com.example.restful.service.PersonService;
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

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public Iterable<Person> getAll() {
		return personService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable int id) {
		return personService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Person> create(@RequestBody Person person) {
		Person saved = personService.save(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable int id, @RequestBody Person person) {
		if (!personService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		person.setId(id);
		return ResponseEntity.ok(personService.save(person));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		if (!personService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		personService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{p_id}/message")
	public ResponseEntity<List<Message>> getMessages(@PathVariable("p_id") int personId) {
		return personService.getMessagesForPerson(personId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{p_id}/message/{m_id}")
	public ResponseEntity<Message> getMessage(@PathVariable("p_id") int personId,
			@PathVariable("m_id") int messageId) {
		return personService.getMessageForPerson(personId, messageId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/{p_id}/message")
	public ResponseEntity<Person> addMessage(@PathVariable("p_id") int personId,
			@RequestBody Message message) {
		return personService.addMessageToPerson(personId, message)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{p_id}/message/{m_id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("p_id") int personId,
			@PathVariable("m_id") int messageId) {
		if (!personService.deleteMessageFromPerson(personId, messageId)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
