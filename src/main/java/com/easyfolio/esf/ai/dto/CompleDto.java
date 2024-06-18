package com.easyfolio.esf.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompleDto {

    private String model = "gpt-3.5-turbo-instruct";
    private String prompt;
    private float temperature = 0.1f;
    @JsonProperty("max_tokens")
    private Integer maxTokens = 1000;

    @Builder
    public CompleDto(String model, String prompt, float temperature, int maxTokens) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = temperature;
        this.maxTokens = maxTokens;
    }

}
