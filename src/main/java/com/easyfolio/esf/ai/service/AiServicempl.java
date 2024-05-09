package com.easyfolio.esf.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.easyfolio.esf.config.AiConfig;
import com.easyfolio.esf.ai.dto.AiCompleDto;
import com.easyfolio.esf.ai.dto.CompleDto;
import com.easyfolio.esf.ai.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AiServicempl implements AiService {

    private final AiConfig aiConfig;

    public AiServicempl(AiConfig aiConfig) {
        this.aiConfig = aiConfig;
    }

    @Value("${openai.url.model}")
    private String modelUrl;

    @Value("${openai.url.model-list}")
    private String modelListUrl;

    @Value("${openai.url.prompt}")
    private String promptUrl;

    @Value("${openai.url.legacy-prompt}")
    private String legacyPromptUrl;

    // 사용 가능한 ai 모델 리스트 조회
    @Override
    public List<Map<String, Object>> modelList() {
        log.debug("[+] 사용 가능한 모델리스트를 조회합니다.");
        List<Map<String, Object>> resultLst = null;

        HttpHeaders headers = aiConfig.httpHeaders();
        ResponseEntity<String> response = aiConfig.restTemplate().exchange(modelUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        try {
            ObjectMapper om = new ObjectMapper();
            Map<String, Object> data = om.readValue(response.getBody(), new TypeReference<>() {
            });

             resultLst = (List<Map<String, Object>>) data.get("data");
            for (Map<String, Object> object : resultLst) {
                log.debug("ID: " + object.get("id"));
                log.debug("Object: " + object.get("object"));
                log.debug("Created: " + object.get("created"));
                log.debug("Owned By: " + object.get("owned_by"));
            }
        } catch (JsonMappingException e) {
            log.debug("JsonMappingException :: {}", e.getMessage());
        } catch (JsonProcessingException e) {
            log.debug("JsonProcessingException :: {}", e.getMessage());
        } catch (RuntimeException e) {
            log.debug("RuntimeException :: {}", e.getMessage());
        }

        return resultLst;
    }

    // ai 모델이 유효한지 조회
    @Override
    public Map<String, Object> isValidModel(String modelName) {
        log.debug("[+] 모델이 유효한지 조회합니다.");
        Map<String, Object> result = new HashMap<>();

        HttpHeaders headers = aiConfig.httpHeaders();
        ResponseEntity<String> response = aiConfig.restTemplate().exchange(modelUrl + "/" + modelName, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        try {
            ObjectMapper om = new ObjectMapper();
            result = om.readValue(response.getBody(), new TypeReference<>() {
            });
            log.debug("Result: " + result);
        } catch (JsonMappingException e) {
            log.debug("JsonMappingException :: {}", e.getMessage());
        } catch (JsonProcessingException e) {
            log.debug("JsonProcessingException :: {}", e.getMessage());
        } catch (RuntimeException e) {
            log.debug("RuntimeException :: {}", e.getMessage());
        }

        return result;
    }

    // ai 레거시 모델 프롬프트 호출
    @Override
    public Map<String, Object> legacyPrompt(CompleDto completionDto) {
        log.debug("[+] 레거시 모델 프롬프트를 호출합니다.");
        Map<String, Object> result = new HashMap<>();

        HttpHeaders headers = aiConfig.httpHeaders();
        HttpEntity<CompleDto> request = new HttpEntity<>(completionDto, headers);
        ResponseEntity<String> response = aiConfig.restTemplate().exchange(legacyPromptUrl, HttpMethod.POST, request, String.class);

        try {
            ObjectMapper om = new ObjectMapper();
            result = om.readValue(response.getBody(), new TypeReference<>() {
            });

        } catch (JsonMappingException e) {
            log.debug("JsonMappingException :: {}", e.getMessage());
        } catch (JsonProcessingException e) {
            log.debug("JsonProcessingException :: {}", e.getMessage());
        } catch (RuntimeException e) {
            log.debug("RuntimeException :: {}", e.getMessage());
        }

        return result;
    }

    // ai 최신 모델 프롬프트 호출
    @Override
    public Map<String, Object> prompt(AiCompleDto aiCompleDto) {
        log.debug("[+] 최신 모델 프롬프트를 호출합니다.");
        Map<String, Object> result = new HashMap<>();

        HttpHeaders headers = aiConfig.httpHeaders();
        HttpEntity<AiCompleDto> request = new HttpEntity<>(aiCompleDto, headers);
        ResponseEntity<String> response = aiConfig.restTemplate().exchange(promptUrl, HttpMethod.POST, request, String.class);

        try {
            ObjectMapper om = new ObjectMapper();
            result = om.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonMappingException e) {
            log.debug("JsonMappingException :: {}", e.getMessage());
        } catch (JsonProcessingException e) {
            log.debug("JsonProcessingException :: {}", e.getMessage());
        } catch (RuntimeException e) {
            log.debug("RuntimeException :: {}", e.getMessage());
        }

        return result;
    }
}
