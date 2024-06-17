package com.easyfolio.esf.otherProtocol.chat.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ChatContentVO {

    private String chatContentId;
    private String roomId;
    private String sendMember;
    private String chatContent;
    private String chatRegDate;

}
