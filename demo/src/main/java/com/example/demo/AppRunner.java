package com.example.demo;

import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	private final Map<String, OpOperation> operations;

	public AppRunner(Map<String, OpOperation> operations) {
		this.operations = operations;
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Введите число a");
		double a = scanner.nextDouble();

		System.out.println("Введите число b");
		double b = scanner.nextDouble();

		System.out.println("Введите тип операции: [adder, divider, multiplier, subtractor]");
		String type = scanner.next();

		System.out.println(operations.get(type).operation(a, b));
	}

}
