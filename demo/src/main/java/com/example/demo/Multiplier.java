package com.example.demo;

import org.springframework.stereotype.Component;

@Component("multiplier")
public class Multiplier implements OpOperation {

	@Override
	public double operation(double a, double b) {
		return a * b;
	}

}
