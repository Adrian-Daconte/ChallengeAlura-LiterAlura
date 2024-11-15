package com.alura.literatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alura.literatura.main.Main;
import com.alura.literatura.repository.BookRepository;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main(repository);
		main.menu();
	}

}
