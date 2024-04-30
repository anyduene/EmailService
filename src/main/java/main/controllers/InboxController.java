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

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InboxController {
    private ReceivedEmail email;
    private String keyword;
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
        if(keyword == null || keyword.isEmpty()) {
            return "redirect:/inbox";
        }
        return "redirect:/inbox/search?keyword=" + keyword;
    }

    @PostMapping("/like")
    public String likeEmail(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findEmailById(emailId);
        emailHandler.markAsLiked(email);
        if(keyword == null || keyword.isEmpty()) {
            return "redirect:/inbox";
        }
        return "redirect:/inbox/search?keyword=" + keyword;
    }

    @PostMapping("/inbox/email-details")
    public String emailDetails(@RequestParam int emailId, Model model) {
        email = emailHandler.findEmailById(emailId);
        model.addAttribute("email", email);
        model.addAttribute("text", ConfidentialDataManager.complexCheck(email.getText()));
        model.addAttribute("fullView", false);
        return "email-details";
    }

    @PostMapping("/inbox/email-details/view-full")
    public String viewFullEmailPost(Model model) {
        model.addAttribute("email", email);
        model.addAttribute("text", email.getText());
        model.addAttribute("fullView", true);
        return "email-details";
    }

    @PostMapping("/mark-as-spam")
    public String markAsSpam(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findEmailById(emailId);
        emailHandler.markAsSpam(email);
        return "redirect:/inbox";
    }

    @PostMapping("/filter")
    public String filterEmails(@RequestParam(value = "liked", required = false) boolean liked,
                               @RequestParam(value = "starred", required = false) boolean starred,
                               Model model) {
        if(!liked && !starred) {
            return "redirect:/inbox";
        }
        List<ReceivedEmail> filteredEmails = receivedEmailsRepository.getReceivedEmails().stream()
                .filter(email -> (liked && email.isLiked) || (starred && email.isStarred))
                .collect(Collectors.toList());
        model.addAttribute("emails", filteredEmails);
        return "inbox";
    }

    @GetMapping("/inbox/search")
    public String searchEmails(@RequestParam String keyword, Model model) {
        if(keyword == null || keyword.isEmpty()) {
            return "redirect:/inbox";
        }
        this.keyword = keyword;
        List<ReceivedEmail> searchResult = receivedEmailsRepository.getReceivedEmails().stream()
                .filter(email -> email.getSubject().contains(keyword) || email.getText().contains(keyword))
                .collect(Collectors.toList());
        model.addAttribute("emails", searchResult);
        return "inbox";
    }
}
