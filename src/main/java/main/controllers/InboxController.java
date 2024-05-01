package main.controllers;

import main.emails.SentEmail;
import main.entities.filters.ConfidentialDataManager;
import main.emails.ReceivedEmail;
import main.entities.repositories.IReceivedEmailsRepository;
import main.entities.models.EmailHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InboxController {
    private ReceivedEmail receivedEmail;
    private SentEmail sentEmail;
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
        model.addAttribute("sent", false);
        model.addAttribute("spam", false);
        this.keyword = null;
        return "inbox";
    }

    @PostMapping("/star")
    public String starEmail(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findReceivedEmailById(emailId);
        emailHandler.markAsStarred(email);
        if(keyword == null || keyword.isEmpty()) {
            return "redirect:/inbox";
        }
        return "redirect:/inbox/search?keyword=" + keyword;
    }

    @PostMapping("/like")
    public String likeEmail(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findReceivedEmailById(emailId);
        emailHandler.markAsLiked(email);
        if(keyword == null || keyword.isEmpty()) {
            return "redirect:/inbox";
        }
        return "redirect:/inbox/search?keyword=" + keyword;
    }

    @PostMapping("/inbox/email-details")
    public String emailDetailsInbox(@RequestParam int emailId, Model model) {
        receivedEmail = emailHandler.findReceivedEmailById(emailId);
        receivedEmail.isViewed = true;
        model.addAttribute("email", receivedEmail);
        model.addAttribute("text", ConfidentialDataManager.complexCheck(receivedEmail.getText()));
        model.addAttribute("fullView", false);
        model.addAttribute("spam", receivedEmail.isSpam);
        model.addAttribute("back", "/inbox");
        model.addAttribute("received", true);
        return "email-details";
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

    @PostMapping("/spam/email-details")
    public String emailDetailsSpam(@RequestParam int emailId, Model model) {
        receivedEmail = emailHandler.findReceivedEmailById(emailId);
        receivedEmail.isViewed = true;
        model.addAttribute("email", receivedEmail);
        model.addAttribute("text", ConfidentialDataManager.complexCheck(receivedEmail.getText()));
        model.addAttribute("fullView", false);
        model.addAttribute("back", "/spam");
        model.addAttribute("spam", true);
        return "email-details";
    }

    @PostMapping("/inbox/email-details/view-full")
    public String viewFullEmailPost(Model model) {
        model.addAttribute("email", receivedEmail);
        model.addAttribute("text", receivedEmail.getText());
        model.addAttribute("fullView", true);
        model.addAttribute("spam", receivedEmail.isSpam);
        return "email-details";
    }

    @PostMapping("/mark-as-spam")
    public String markAsSpam(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findReceivedEmailById(emailId);
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
        model.addAttribute("sent", false);
        model.addAttribute("spam", false);
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
        model.addAttribute("sent", false);
        model.addAttribute("spam", false);
        return "inbox";
    }

    @PostMapping("/recover")
    public String recoverEmail(@RequestParam int emailId) {
        ReceivedEmail email = emailHandler.findReceivedEmailById(emailId);
        emailHandler.recoverFromSpam(email);
        return "redirect:/inbox";
    }
}
