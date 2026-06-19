package com.example.demo;

import org.springframework.stereotype.Component;

@Component("divider")
public class Divider implements OpOperation {

	@Override
	public double operation(double a, double b) {
		return a / b;
	}

}
