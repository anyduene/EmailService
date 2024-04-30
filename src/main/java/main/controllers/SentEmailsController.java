package main.controllers;

import main.entities.models.EmailHandler;
import main.entities.repositories.ISentEmailsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SentEmailsController {
    private final EmailHandler emailHandler;
    private final ISentEmailsRepository sentEmailsRepository;

    public SentEmailsController(ISentEmailsRepository sentEmailsRepository, EmailHandler emailHandler) {
        this.sentEmailsRepository = sentEmailsRepository;
        this.emailHandler = emailHandler;
    }

    @GetMapping("/sent")
    public String sent(Model model) {
        model.addAttribute("emails", sentEmailsRepository.getSentEmails());
        model.addAttribute("sent", true);
        model.addAttribute("received", false);
        model.addAttribute("spam", false);
        return "inbox";
    }
}
