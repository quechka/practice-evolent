package com.example.weather.config;

import com.example.weather.model.Weather;
import com.example.weather.service.WeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherDataLoader {

	@Bean
	CommandLineRunner initWeather(WeatherService weatherService) {
		return args -> {
			if (weatherService.count() == 0) {
				weatherService.save(new Weather(55.7558, 37.6173, -5.0, "Облачно"));
				weatherService.save(new Weather(59.9343, 30.3351, -8.0, "Снег"));
				weatherService.save(new Weather(55.0084, 82.9357, -15.0, "Ясно"));
			}
		};
	}
}
