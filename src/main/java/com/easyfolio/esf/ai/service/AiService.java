package com.easyfolio.esf.ai.service;

import com.theokanning.openai.completion.chat.ChatMessage;

public interface AiService {
    String generateResponse(String prompt, ChatMessage userMessage);
}
