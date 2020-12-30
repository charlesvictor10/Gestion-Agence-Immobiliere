package dev.charles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionImmobiliereApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(GestionImmobiliereApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
