package main.controllers;

import main.emails.SentEmail;
import main.entities.filters.ConfidentialDataManager;
import main.entities.models.EmailHandler;
import main.entities.repositories.ISentEmailsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SentEmailsController {
    private SentEmail sentEmail;
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
        model.addAttribute("back", "/sent");
        return "inbox";
    }

    @PostMapping("/sent/email-details")
    public String emailDetailsSent(@RequestParam int emailId, Model model) {
        sentEmail = emailHandler.findSentEmailById(emailId);
        model.addAttribute("email", sentEmail);
        model.addAttribute("text", ConfidentialDataManager.complexCheck(sentEmail.getText()));
        model.addAttribute("fullView", false);
        model.addAttribute("back", "/sent");
        model.addAttribute("sent", true);
        return "email-details";
    }

    @PostMapping("/sent/email-details/view-full")
    public String viewFullEmailPostSpam(Model model) {
        model.addAttribute("email", sentEmail);
        model.addAttribute("text", sentEmail.getText());
        model.addAttribute("fullView", true);
        model.addAttribute("back", "/sent");
        model.addAttribute("sent", true);
        return "email-details";
    }
}
