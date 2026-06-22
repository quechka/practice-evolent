package com.example.weather.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weather {

	@Id
	@GeneratedValue
	private int id;

	@NonNull
	private double latitude;

	@NonNull
	private double longitude;

	@NonNull
	private double temperature;

	@NonNull
	private String description;

	public Weather(double latitude, double longitude, double temperature, @NonNull String description) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.temperature = temperature;
		this.description = description;
	}
}
