package com.easyfolio.esf.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transfer {
    //복수개의 매개변수를 List로
    static public <T> List<T> asList(T...items){
        List<T> list = new ArrayList<>();
        for(T each : items){
            list.add(each);
        }
        return list;
    }
    //원래 리스에 복수개의 인수를 추가
    static public <T>List<T> asList(List<T> list, T...items){
        for(T each : items){
            list.add(each);
        }
        return list;
    }
    // ,이 있는 문자열을 리스트로 반환
    static public <T>List<T> asList(String str){
        List<T> list = new ArrayList<>();
        while(str.contains(",")){

        }

        return list;
    }

}
