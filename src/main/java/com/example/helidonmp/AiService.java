package com.example.helidonmp;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AiService {

    public String completeEcho(String prompt) {
        if (prompt == null || prompt.isBlank()) {
            return "";
        }
        return "Echo: " + prompt;
    }
}


