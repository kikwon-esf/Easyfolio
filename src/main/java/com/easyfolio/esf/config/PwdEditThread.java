package com.easyfolio.esf.config;

import com.easyfolio.esf.config.interceptor.PwdEditInterceptor;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class PwdEditThread extends Thread{
    private String userSessionId;
    public PwdEditThread(String userSessionId){
        this.userSessionId = userSessionId;
    }
    @Override
    public void run() {
        PwdEditInterceptor.set.add(userSessionId);
        try {
            Thread.sleep(10000);
            PwdEditInterceptor.set.remove(userSessionId);
        }catch (Exception e){
            e.printStackTrace();
            PwdEditInterceptor.set.remove(userSessionId);
        }
    }
}
