package main;

import main.emails.ReceivedEmail;
import main.emails.SentEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {

	public static final List<SentEmail> sent_emails = new ArrayList<>();
	public static final List<ReceivedEmail> received_emails = new ArrayList<>();
	public static final List<ReceivedEmail> starred_emails = new ArrayList<>();
	public static final List<ReceivedEmail> liked_emails = new ArrayList<>();
	public static final List<ReceivedEmail> spam_emails = new ArrayList<>();
	public static final List<String> black_list_domains = new ArrayList<>();
	public static final List<String> white_list_domains = new ArrayList<>();
	public static final List<String> black_list_content = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
