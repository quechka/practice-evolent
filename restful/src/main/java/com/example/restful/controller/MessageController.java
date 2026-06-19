package com.example.restful.controller;

import com.example.restful.dto.Message;
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
@RequestMapping("/message")
public class MessageController {

	private final List<Message> messages = new ArrayList<>();
	private int nextId = 1;

	@GetMapping
	public List<Message> getAll() {
		return messages;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Message> getById(@PathVariable int id) {
		return findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Message> create(@RequestBody Message message) {
		message.setId(nextId++);
		messages.add(message);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Message> update(@PathVariable int id, @RequestBody Message message) {
		Optional<Message> existing = findById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		message.setId(id);
		messages.set(messages.indexOf(existing.get()), message);
		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		Optional<Message> existing = findById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		messages.remove(existing.get());
		return ResponseEntity.noContent().build();
	}

	private Optional<Message> findById(int id) {
		return messages.stream()
				.filter(message -> message.getId() == id)
				.findFirst();
	}
}
