package main.controllers;

import filters.ConfidentialDataManager;
import main.emails.ReceivedEmail;
import main.entities.IReceivedEmailsRepository;
import main.entities.models.EmailHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InboxController {
    private ReceivedEmail email;
    private final EmailHandler emailHandler;
    private final IReceivedEmailsRepository receivedEmailsRepository;

    public InboxController(IReceivedEmailsRepository receivedEmailsRepository, EmailHandler emailHandler) {
        this.receivedEmailsRepository = receivedEmailsRepository;
        this.emailHandler = emailHandler;
    }

    @GetMapping("/inbox")
    public String inbox(Model model) {
        model.addAttribute("emails", receivedEmailsRepository.getReceivedEmails());
        return "inbox";
    }

    @PostMapping("/star")
    public String starEmail(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findEmailById(emailId);
        emailHandler.markAsStarred(email);
        return "redirect:/inbox";
    }

    @PostMapping("/like")
    public String likeEmail(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findEmailById(emailId);
        emailHandler.markAsLiked(email);
        return "redirect:/inbox";
    }

    @PostMapping("/inbox/email-details")
    public String emailDetails(@RequestParam int emailId, Model model) {
        email = emailHandler.findEmailById(emailId);
        ReceivedEmail hiddenEmail = new ReceivedEmail(email.getEmail(), email.getSubject(), ConfidentialDataManager.complexCheck(email.getText()), email.getName());
        System.out.println(email.getText());
        model.addAttribute("email", hiddenEmail);
        return "email-details";
    }

    @PostMapping("/inbox/email-details/view-full")
    public String viewFullEmailPost(Model model) {
        model.addAttribute("email", email);
        return "view-full";
    }

    @PostMapping("/mark-as-spam")
    public String markAsSpam(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findEmailById(emailId);
        emailHandler.markAsSpam(email);
        return "redirect:/inbox";
    }
}
