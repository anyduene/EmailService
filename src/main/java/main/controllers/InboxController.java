package main.controllers;

import main.Main;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InboxController {

    @GetMapping("/inbox")
    public String inbox(Model model) {
        model.addAttribute("emails", Main.received_emails);
        return "inbox";
    }
}
