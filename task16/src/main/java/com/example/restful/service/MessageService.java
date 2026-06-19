package com.example.restful.service;

import com.example.restful.dto.Message;
import com.example.restful.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

	private final MessageRepository messageRepository;

	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public Iterable<Message> findAll() {
		return messageRepository.findAll();
	}

	public Optional<Message> findById(int id) {
		return messageRepository.findById(id);
	}

	public Message save(Message message) {
		return messageRepository.save(message);
	}

	public boolean existsById(int id) {
		return messageRepository.existsById(id);
	}

	public void deleteById(int id) {
		messageRepository.deleteById(id);
	}
}
