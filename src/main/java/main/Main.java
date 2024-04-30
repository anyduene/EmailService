package main;

import main.entities.EmailsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		EmailsRepository.generateReceivedEmails();
		EmailsRepository.generateSentEmails();
		SpringApplication.run(Main.class, args);
	}

}
