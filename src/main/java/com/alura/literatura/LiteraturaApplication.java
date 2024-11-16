package com.alura.literatura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.literatura.main.Main;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	private Main main;

	public LiteraturaApplication(Main main) {
		this.main = main;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		main.menu();
	}
}
