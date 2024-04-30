package main.controllers;

import main.emails.ReceivedEmail;
import main.entities.models.EmailHandler;
import main.entities.repositories.IReceivedEmailsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpamEmailsController {
    private final EmailHandler emailHandler;
    private final IReceivedEmailsRepository receivedEmailsRepository;

    public SpamEmailsController(IReceivedEmailsRepository receivedEmailsRepository, EmailHandler emailHandler) {
        this.receivedEmailsRepository = receivedEmailsRepository;
        this.emailHandler = emailHandler;
    }

    @GetMapping("/spam")
    public String spam(Model model) {
        model.addAttribute("emails", receivedEmailsRepository.getSpamEmails());
        model.addAttribute("spam", true);
        model.addAttribute("received", false);
        model.addAttribute("sent", false);
        return "inbox";
    }
}
