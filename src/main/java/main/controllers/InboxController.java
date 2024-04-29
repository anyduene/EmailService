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
    private static int count = 0;
    private final EmailHandler emailHandler;
    private final IReceivedEmailsRepository receivedEmailsRepository;

    public InboxController(IReceivedEmailsRepository receivedEmailsRepository, EmailHandler emailHandler) {
        this.receivedEmailsRepository = receivedEmailsRepository;
        this.emailHandler = emailHandler;
        count++;
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
        System.out.println(count);
        return "redirect:/inbox";
    }

    @GetMapping("/email-details")
    public String emailDetailsGet(Model model) {

        return "email-details";
    }

    @PostMapping("/email-details")
    public String emailDetails(@RequestParam int emailId, Model model) {
        ReceivedEmail email = emailHandler.findEmailById(emailId);
        email.text = ConfidentialDataManager.complexCheck(email.text);
        model.addAttribute("email", email);
        return "email-details";
    }

    @PostMapping("/view-full")
    public String viewFullEmailPost(@RequestParam int emailId, Model model) {
        ReceivedEmail email = emailHandler.findEmailById(emailId);
        model.addAttribute("email", email);
        return "redirect:/email-details";
    }

    @PostMapping("/mark-as-spam")
    public String markAsSpam(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findEmailById(emailId);
        emailHandler.markAsSpam(email);
        return "redirect:/inbox";
    }

}
