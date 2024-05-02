package main.controllers;

import main.emails.ReceivedEmail;
import main.entities.filters.ConfidentialDataManager;
import main.entities.models.EmailHandler;
import main.entities.repositories.IReceivedEmailsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpamEmailsController {
    private ReceivedEmail spamEmail;
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
        model.addAttribute("back", "/spam");
        return "inbox";
    }

    @PostMapping("/spam/email-details")
    public String emailDetailsSpam(@RequestParam int emailId, Model model) {
        spamEmail = emailHandler.findReceivedEmailById(emailId);
        spamEmail.isViewed = true;
        model.addAttribute("email", spamEmail);
        model.addAttribute("text", ConfidentialDataManager.complexCheck(spamEmail.getText()));
        model.addAttribute("fullView", false);
        model.addAttribute("back", "/spam");
        model.addAttribute("spam", true);
        return "email-details";
    }

    @PostMapping("/spam/email-details/view-full")
    public String viewFullEmailPostSpam(Model model) {
        model.addAttribute("email", spamEmail);
        model.addAttribute("text", spamEmail.getText());
        model.addAttribute("fullView", true);
        model.addAttribute("back", "/spam");
        model.addAttribute("spam", true);
        return "email-details";
    }
}
