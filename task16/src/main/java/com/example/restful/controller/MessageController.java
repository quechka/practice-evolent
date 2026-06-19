package com.example.restful.controller;

import com.example.restful.dto.Message;
import com.example.restful.service.MessageService;
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
@RequestMapping("/message")
public class MessageController {

	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping
	public Iterable<Message> getAll() {
		return messageService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Message> getById(@PathVariable int id) {
		return messageService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Message> create(@RequestBody Message message) {
		Message saved = messageService.save(message);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Message> update(@PathVariable int id, @RequestBody Message message) {
		if (!messageService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		message.setId(id);
		return ResponseEntity.ok(messageService.save(message));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		if (!messageService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		messageService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
