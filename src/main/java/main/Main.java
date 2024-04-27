package main;

import main.emails.ReceivedEmail;
import main.emails.SentEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
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

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home page");
		return "home";
	}

}
