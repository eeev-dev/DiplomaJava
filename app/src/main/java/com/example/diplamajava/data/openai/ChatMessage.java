package com.example.diplamajava.data.openai;
public class ChatMessage {
    public String role;
    public String content;

    // Конструктор для chatgpt
    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}

