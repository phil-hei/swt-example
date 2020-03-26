package de.fhdo.swt.example.swtexampleapplication.controller;

import de.fhdo.swt.example.swtexampleapplication.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute User user) {
        return "user";
    }
}
