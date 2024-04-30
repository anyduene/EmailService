package main.controllers;

import main.entities.repositories.ISpamFilterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final ISpamFilterRepository spamFilterRepository;

    public MainController(ISpamFilterRepository spamFilterRepository) {
        this.spamFilterRepository = spamFilterRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home page");
        return "index";
    }

    @PostMapping("/add-black-domain")
    public String addBlackDomain(@RequestParam String black_domain) {
        spamFilterRepository.addBlackListDomain(black_domain);
        return "redirect:/";
    }

    @PostMapping("/add-white-domain")
    public String addWhiteDomain(@RequestParam String white_domain) {
        spamFilterRepository.addWhiteListDomain(white_domain);
        return "redirect:/";
    }

    @PostMapping("/add-black-content")
    public String addBlackContent(@RequestParam String black_content) {
        spamFilterRepository.addBlackListContent(black_content);
        return "redirect:/";
    }

}