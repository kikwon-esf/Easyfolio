package com.easyfolio.esf.ai.controller;

import com.easyfolio.esf.ai.service.AiService;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @Value("${OPEN_API_KEY}")
    private String openAiApiKey;

    @GetMapping("/assistant")
    public ResponseEntity<String> recommendFood(@RequestParam String prompt) {
        try {
            // 1. 사용자 메시지 생성
            ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);

            // 2. 챗봇 응답 생성
            String response = aiService.generateResponse(prompt, userMessage);

            // 챗봇 응답 반환
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // 예상치 못한 오류 처리
            return new ResponseEntity<>("오류가 발생했습니다. 관리자에게 문의해주세요.", HttpStatus.OK);  // 오류 상태 코드 유지
        }
    }
}
