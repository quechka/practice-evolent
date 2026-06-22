package com.example.weather.controller;

import com.example.weather.model.Weather;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping
	public Iterable<Weather> getAllWeather() {
		return weatherService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Weather> getWeatherById(@PathVariable int id) {
		return weatherService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/coordinates")
	public ResponseEntity<Weather> getWeatherByCoordinates(
			@RequestParam double latitude,
			@RequestParam double longitude) {
		return weatherService.findByCoordinates(latitude, longitude)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Weather> addWeather(@RequestBody Weather weather) {
		if (weather.getId() != 0 && weatherService.existsById(weather.getId())) {
			return ResponseEntity.badRequest().build();
		}
		Weather saved = weatherService.save(weather);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
}
