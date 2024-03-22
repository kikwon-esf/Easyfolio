package com.easyfolio.esf.crud;

import com.easyfolio.esf.myPage.service.MyPageService;
import com.easyfolio.esf.myPage.vo.FavoriteVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class FavoriteTest {
    @Autowired
    private MyPageService myPageService;


    public FavoriteVO addFavoriteTest(String foodCode, String memberId){
        FavoriteVO input = new FavoriteVO();
        input.setFoodCode(foodCode);
        input.setMemberId(memberId);
        return input;
    }
    @Test
    @Transactional
    public void readFavorite(){

        FavoriteVO input = new FavoriteVO();
        String foodCode = "FOOD_000055";
        String first = "FOOD_";
        String second = "0000";
        int code = 60;

        String memberId = "wltjd";

        for(int i = 0 ; i < 5 ; i++){
            input = addFavoriteTest((first+second+code++), memberId);
            System.err.println("code : " + code);
            myPageService.addFavorite(input);
        }
//        System.err.println("result : " + myPageService.getFavoriteListByMember("wltjd"));
        ;

    }

    public static void main(String[] args) {

    }
}
