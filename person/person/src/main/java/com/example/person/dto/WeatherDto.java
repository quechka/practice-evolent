package com.example.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

	private int id;
	private double latitude;
	private double longitude;
	private double temperature;
	private String description;
}
