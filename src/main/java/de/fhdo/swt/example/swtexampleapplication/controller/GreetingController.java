package de.fhdo.swt.example.swtexampleapplication.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
    @Value("${welcome.message}")
    private String message;

    @RequestMapping("/")
    @ResponseBody
    public String greeting() {
        return message;
    }
}
