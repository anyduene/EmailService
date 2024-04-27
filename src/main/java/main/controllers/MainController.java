package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home page");
        return "index";
    }

//    @GetMapping("/write-email")
//    public String writeEmail(Model model) {
//        model.addAttribute("title", "Write email");
//        return "write-email";
//    }

}