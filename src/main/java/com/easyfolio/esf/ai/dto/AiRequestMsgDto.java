package com.easyfolio.esf.ai.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiRequestMsgDto {

    private String role;

    private String content;

    @Builder
    public AiRequestMsgDto(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
