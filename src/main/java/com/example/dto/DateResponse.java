package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class DateResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", locale = "en", timezone = "GMT")
    private LocalDateTime date;

    public DateResponse(LocalDateTime date) {
        this.date = date;
    }
}
