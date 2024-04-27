package main.controllers;

import main.emails.Email;
import main.emails.ReceivedEmail;
import main.emails.SentEmail;
import main.Main;
import models.EmailHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @GetMapping("/write-email")
    public String showComposeForm(Model model) {
        model.addAttribute("email", new SentEmail("", "", ""));
        return "write-email";
    }

    @PostMapping("/write-email")
    public String handleFormSubmit(@RequestParam String receiver_email, @RequestParam String subject, @RequestParam String text) {
        EmailHandler.sendEmail(receiver_email, subject, text);
        for (SentEmail sentEmail : Main.sent_emails) {
            System.out.println("To: " + sentEmail.getEmail() + " Subject: " + sentEmail.getSubject() + " Text: " + sentEmail.getText());
        }
        return "redirect:/";
    }

}