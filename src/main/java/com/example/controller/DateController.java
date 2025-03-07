package com.example.controller;

import com.example.dto.DateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/date")
public class DateController {

    @GetMapping
    public DateResponse getFormattedDate() {
        return new DateResponse(LocalDateTime.now());
    }
}