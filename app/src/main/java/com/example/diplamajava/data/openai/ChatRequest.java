package com.example.diplamajava.data.openai;

import java.util.List;

public class ChatRequest {
    public String model = "gpt-4o-mini-2024-07-18";
    public List<ChatMessage> messages;
    public double temperature = 0.7;

    public ChatRequest(List<ChatMessage> messages) {
        this.messages = messages;
    }
}

