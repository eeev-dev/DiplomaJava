package com.example.diplamajava.data.openai;

import java.util.List;

public class ChatResponse {
    public List<Choice> choices;

    public static class Choice {
        public ChatMessage message;
    }
}
