package com.example.restful.service;

import com.example.restful.dto.Message;
import com.example.restful.dto.Person;
import com.example.restful.repository.MessageRepository;
import com.example.restful.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

	private final PersonRepository personRepository;
	private final MessageRepository messageRepository;

	public PersonService(PersonRepository personRepository, MessageRepository messageRepository) {
		this.personRepository = personRepository;
		this.messageRepository = messageRepository;
	}

	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}

	public Optional<Person> findById(int id) {
		return personRepository.findById(id);
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public boolean existsById(int id) {
		return personRepository.existsById(id);
	}

	public void deleteById(int id) {
		personRepository.deleteById(id);
	}

	public Optional<Person> addMessageToPerson(int personId, Message message) {
		Optional<Person> personOptional = personRepository.findById(personId);
		if (personOptional.isEmpty()) {
			return Optional.empty();
		}
		Person person = personOptional.get();
		message.setPerson(person);
		message.setTime(LocalDateTime.now());
		person.addMessage(message);
		return Optional.of(personRepository.save(person));
	}

	public Optional<List<Message>> getMessagesForPerson(int personId) {
		return personRepository.findById(personId)
				.map(Person::getMessages);
	}

	public Optional<Message> getMessageForPerson(int personId, int messageId) {
		return personRepository.findById(personId)
				.flatMap(person -> person.getMessages().stream()
						.filter(message -> message.getId() == messageId)
						.findFirst());
	}

	public boolean deleteMessageFromPerson(int personId, int messageId) {
		Optional<Person> personOptional = personRepository.findById(personId);
		if (personOptional.isEmpty()) {
			return false;
		}
		Person person = personOptional.get();
		Optional<Message> messageOptional = person.getMessages().stream()
				.filter(message -> message.getId() == messageId)
				.findFirst();
		if (messageOptional.isEmpty()) {
			return false;
		}
		Message message = messageOptional.get();
		person.getMessages().remove(message);
		messageRepository.delete(message);
		personRepository.save(person);
		return true;
	}
}
