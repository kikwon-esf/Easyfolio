package com.easyfolio.esf.myPage.vo;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Data
public class FavoriteInputVO {
    private String memberId;
    private String foodCode;
}
