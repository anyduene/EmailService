package main.controllers;

import main.emails.ReceivedEmail;
import main.emails.SentEmail;
import models.EmailHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "redirect:/";
    }

    @GetMapping("/receive-email")
    public String showReceiveForm(Model model) {
        model.addAttribute("email", new ReceivedEmail("", "", "", ""));
        return "receive-email";
    }

    @PostMapping("/receive-email")
    public String handleReceiveFormSubmit(@RequestParam String sender_email, @RequestParam String subject, @RequestParam String text, @RequestParam String name) {
        EmailHandler.receiveEmail(sender_email, subject, text, name);
        return "redirect:/";
    }

}