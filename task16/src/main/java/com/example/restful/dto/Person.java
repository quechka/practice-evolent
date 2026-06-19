package com.example.restful.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String surname;
	private String lastname;
	private String birthday;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<>();

	public Person() {
	}

	public Person(String firstname, String surname, String lastname, String birthday) {
		this.firstname = firstname;
		this.surname = surname;
		this.lastname = lastname;
		this.birthday = birthday;
	}

	public Person(int id, String firstname, String surname, String lastname, String birthday) {
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.lastname = lastname;
		this.birthday = birthday;
	}

	public Person(int id, String firstname, String surname, String lastname, String birthday,
			List<Message> messages) {
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.messages = messages;
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
