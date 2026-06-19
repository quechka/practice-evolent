package com.example.demo;

import org.springframework.stereotype.Component;

@Component("adder")
public class Adder implements OpOperation {

	@Override
	public double operation(double a, double b) {
		return a + b;
	}

}
