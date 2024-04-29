package main.controllers;

import filters.ConfidentialDataManager;
import main.Main;
import main.emails.ReceivedEmail;
import models.EmailHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static models.EmailHandler.findEmailById;

@Controller
public class InboxController {

    @GetMapping("/inbox")
    public String inbox(Model model) {
        model.addAttribute("emails", Main.received_emails);
        return "inbox";
    }

    @PostMapping("/star")
    public String starEmail(@RequestParam int emailId) {
        ReceivedEmail email = findEmailById(emailId);
        EmailHandler.markAsStarred(email);
        return "redirect:/inbox";
    }

    @PostMapping("/like")
    public String likeEmail(@RequestParam int emailId) {
        ReceivedEmail email = findEmailById(emailId);
        EmailHandler.markAsLiked(email);
        return "redirect:/inbox";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/email-details")
    public String emailDetails(@RequestParam int emailId, Model model) {
        ReceivedEmail email = findEmailById(emailId);
        email.text = ConfidentialDataManager.complexCheck(email.text);
        model.addAttribute("email", email);
        return "email-details";
    }

    @PostMapping("/view-full")
    public String viewFullEmailPost(@RequestParam int emailId, Model model) {
        ReceivedEmail email = findEmailById(emailId);
        model.addAttribute("email", email);
        return "email-details";
    }

    @PostMapping("/mark-as-spam")
    public String markAsSpam(@RequestParam int emailId) {
        ReceivedEmail email = findEmailById(emailId);
        EmailHandler.markAsSpam(email);
        return "redirect:/inbox";
    }

}
