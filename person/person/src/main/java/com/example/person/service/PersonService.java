package com.example.person.service;

import com.example.person.dto.LocationDto;
import com.example.person.dto.WeatherDto;
import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${location.service.url}")
	private String locationServiceUrl;

	@Value("${weather.service.url}")
	private String weatherServiceUrl;

	public Iterable<Person> findAll() {
		return repository.findAll();
	}

	public Optional<Person> findById(int id) {
		return repository.findById(id);
	}

	public Person save(Person person) {
		return repository.save(person);
	}

	public boolean existsById(int id) {
		return repository.existsById(id);
	}

	public Optional<LocationDto> getLocationForPerson(int id) {
		return repository.findById(id).flatMap(this::fetchLocation);
	}

	public Optional<WeatherDto> getWeatherForPerson(int id) {
		return getLocationForPerson(id).flatMap(this::fetchWeather);
	}

	private Optional<LocationDto> fetchLocation(Person person) {
		try {
			LocationDto location = restTemplate.getForObject(
					locationServiceUrl + "/location/name/{name}",
					LocationDto.class,
					person.getLocation());
			return Optional.ofNullable(location);
		} catch (HttpClientErrorException.NotFound e) {
			return Optional.empty();
		}
	}

	private Optional<WeatherDto> fetchWeather(LocationDto location) {
		try {
			WeatherDto weather = restTemplate.getForObject(
					weatherServiceUrl + "/weather/coordinates?latitude={lat}&longitude={lon}",
					WeatherDto.class,
					location.getLatitude(),
					location.getLongitude());
			return Optional.ofNullable(weather);
		} catch (HttpClientErrorException.NotFound e) {
			return Optional.empty();
		}
	}
}
