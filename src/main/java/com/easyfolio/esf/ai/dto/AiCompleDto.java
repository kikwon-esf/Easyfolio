package com.easyfolio.esf.ai.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiCompleDto {

    private String model;

    private List<AiRequestMsgDto> messages;

    @Builder
    public AiCompleDto(String model, List<AiRequestMsgDto> messages) {
        this.model = model;
        this.messages = messages;
    }
}
