package main.controllers;

import filters.ConfidentialDataManager;
import main.emails.ReceivedEmail;
import main.entities.repositories.IReceivedEmailsRepository;
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
        model.addAttribute("received", true);
        this.keyword = null;
        System.out.println("receivedEmailsRepository.getReceivedEmails()");
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
        email.isViewed = true;
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
        List<ReceivedEmail> filteredEmails = receivedEmailsRepository.getReceivedEmails().stream()
                .filter(email -> (liked && email.isLiked) || (starred && email.isStarred))
                .collect(Collectors.toList());
        if((!liked && !starred) || filteredEmails.isEmpty()) {
            return "redirect:/inbox";
        }
        model.addAttribute("emails", filteredEmails);
        model.addAttribute("received", true);
        return "inbox";
    }

    @GetMapping("/inbox/search")
    public String searchEmails(@RequestParam String keyword, Model model) {
        this.keyword = keyword;
        List<ReceivedEmail> searchResult = receivedEmailsRepository.getReceivedEmails().stream()
                .filter(email -> email.getSubject().contains(keyword) || email.getText().contains(keyword))
                .collect(Collectors.toList());
        if(searchResult.isEmpty()) {
            return "redirect:/inbox";
        }
        model.addAttribute("emails", searchResult);
        model.addAttribute("received", true);
        return "inbox";
    }
}
