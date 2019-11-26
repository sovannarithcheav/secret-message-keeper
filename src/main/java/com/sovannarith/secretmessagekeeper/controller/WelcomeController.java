package com.sovannarith.secretmessagekeeper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class WelcomeController {

    @GetMapping({"/", "/index"})
    public String main() throws IOException {
        return "index"; //view
    }


}