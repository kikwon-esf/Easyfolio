package com.easyfolio.esf.ai.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiServicempl implements AiService {


    @Value("${OPEN_API_KEY}")
    private String openAiApiKey;

    @Override
    public String generateResponse(String prompt, ChatMessage userMessage) {
        OpenAiService openAiService = new OpenAiService(openAiApiKey);

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage(ChatMessageRole.ASSISTANT.value(), "나는 음식 관련 도우미인 Dishcovery라는 챗봇이야. 음식을 추천하고 도와줄게"));
        ChatMessage chatMessage2 = new ChatMessage(ChatMessageRole.USER.value(), prompt);
        messages.add(1, chatMessage2);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-3")
                .messages(messages)
                .maxTokens(1000)
                .temperature(0.0)
                .build();

        ChatCompletionResult chatCompletionResult = openAiService.createChatCompletion(chatCompletionRequest);
        String recommendation = chatCompletionResult.getChoices().get(0).getMessage().getContent();

        System.out.println("사용자 입력: " + prompt);
        System.out.println("AI 응답: " + recommendation);

        return recommendation;
    }
}
