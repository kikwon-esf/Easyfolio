package com.easyfolio.esf.crud;

import com.easyfolio.esf.myPage.service.MyPageService;
import com.easyfolio.esf.myPage.vo.FavoriteInputVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class FavoriteTest {
    private MyPageService myPageService;


    public FavoriteInputVO addFavoriteTest(String foodCode, String memberId){
        FavoriteInputVO input = new FavoriteInputVO();
        input.setFoodCode(foodCode);
        input.setMemberId(memberId);
        return input;
    }
    @Test
    @Transactional
    public void readFavorite(){

        FavoriteInputVO input = new FavoriteInputVO();
        String foodCode = "FOOD_000055";
        String memberId = "wltjd";
        System.err.println(Integer.parseInt(foodCode));
//        for(int i = 0 ; i < 5 ; i++){
//            input = addFavoriteTest(foodCode, memberId);
//            myPageService.addFavorite(input);
//        }

    }

    public static void main(String[] args) {

    }
}
