package com.easyfolio.esf.myPage.vo;

import java.util.List;

public class CartSessionState {

    private SessionState state;
    private List<String> breakUrlList;
    private String targetPage;
    private String ajax;
//    private String
    private static enum SessionState {
        INIT, READ, WRITE
    }
}
