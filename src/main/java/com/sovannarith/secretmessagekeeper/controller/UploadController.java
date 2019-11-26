package com.sovannarith.secretmessagekeeper.controller;

import com.sovannarith.secretmessagekeeper.service.MessageUploadService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class UploadController {

    private final MessageUploadService uploadService;

    public UploadController(MessageUploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping("/upload")
    public String retrieve(@RequestParam("ts") String ts) throws IOException, URISyntaxException {
        return uploadService.retrieve(ts);
    }

    @PostMapping("/upload")
    public String post(@RequestParam("message") String message) throws IOException {
        return uploadService.post(message);
    }


}
