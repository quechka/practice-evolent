package com.example.demo;

import org.springframework.stereotype.Component;

@Component("subtractor")
public class Subtractor implements OpOperation {

	@Override
	public double operation(double a, double b) {
		return a - b;
	}

}
