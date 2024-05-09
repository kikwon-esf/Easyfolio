package com.easyfolio.esf.ai.service;

import com.easyfolio.esf.ai.dto.AiCompleDto;
import com.easyfolio.esf.ai.dto.CompleDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AiService {
    List<Map<String, Object>> modelList();

    Map<String, Object> legacyPrompt(CompleDto completionDto);

    Map<String, Object> prompt(AiCompleDto aiCompleDto);

    Map<String, Object> isValidModel(String modelName);
}
