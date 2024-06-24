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
    static public List<String> asList(String str){
        List<String> list = new ArrayList<>();
        while(str.contains(",")){
            int index = str.indexOf(',');
            list.add(0,str.substring(index));
            str = str.substring(index+1);
        }
        list.add(str);
        return list;
    }
    //,이 있는 문자열을 리스트에 추가
    static public List<String> asList(List<String> list, String str){
        while(str.contains(",")){
            int index = str.indexOf(',');
            list.add(0,str.substring(index));
            str = str.substring(index+1);
        }
        list.add(str);
        return list;
    }
    //공백문자열검사
    public static boolean reqexTest(String str){

        return !(str.matches("(?s).*\\S.*"));
        //||str.matches("^\\\\s*$")
    }
}
