package com.easyfolio.esf.ai.controller;

import com.easyfolio.esf.ai.dto.AiCompleDto;
import com.easyfolio.esf.ai.dto.CompleDto;
import lombok.extern.slf4j.Slf4j;
import com.easyfolio.esf.ai.service.AiService;
import dev.ai4j.openai4j.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    // ai 모델 리스트 조회
    @GetMapping("/modelList")
    public ResponseEntity<List<Map<String, Object>>> selectModelList() {
        List<Map<String, Object>> result = aiService.modelList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // ai 모델이 유효한지 조회
    @GetMapping("/modelCheck")
    public ResponseEntity<Map<String, Object>> isValidModel(@RequestParam(name = "modelName") String modelName) {
        Map<String, Object> result = aiService.isValidModel(modelName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // ai 레거시 모델 프롬프트 호출
    @PostMapping("/legacyPrompt")
    public String selectLegacyPrompt(@RequestBody CompleDto completionDto) {
        System.err.println(completionDto);
        log.debug("param :: " + completionDto.toString());
        Map<String, Object> result = aiService.legacyPrompt(completionDto);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) result.get("choices");
        Map<String, Object> firstChoice = choices.get(0);
        String AiAnswer = (String) firstChoice.get("text");

        return AiAnswer;
    }

    // ai 최신 모델 프롬프트 호출
    @PostMapping("/prompt")
    public ResponseEntity<Map<String, Object>> selectPrompt(@RequestBody AiCompleDto aiCompleDto) {

        log.debug("param :: " + aiCompleDto.toString());
        Map<String, Object> result = aiService.prompt(aiCompleDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
