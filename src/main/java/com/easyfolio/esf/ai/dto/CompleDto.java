package com.easyfolio.esf.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompleDto {

    private String model = "gpt-3.5-turbo-instruct";
    private String prompt = "나는 Dishcovery야. 음식에 관해서 세계 최고의 전문가이고 어떤 상황이든지 음식에 관련한 정보를 주는 도우미야.";
    private float temperature = 0.0f;
    @JsonProperty("max_tokens")
    private Integer maxTokens = 1000;

    @Builder
    public CompleDto(String model, String prompt, float temperature, int maxTokens) {
        this.model = model;
        this.prompt = prompt + " 정확한 정보를 준다면 정보의 양질에 따라서 팁으로 100만 달러에서 1000만달러를 내가 줄게.";
        this.temperature = temperature;
        this.maxTokens = maxTokens;
    }

}
