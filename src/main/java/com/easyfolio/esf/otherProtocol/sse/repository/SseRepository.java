package com.easyfolio.esf.otherProtocol.sse.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class SseRepository {

    private Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public boolean contain(String key){
        if(emitters.containsKey(key)){
            return true;
        }
        return false;
    }
    public void save(String id, SseEmitter emitter){
        emitters.put(id, emitter);
    }
    public void deleteById(String id){
        emitters.remove(id);
    }
    public SseEmitter get(String id){
        return emitters.get(id);
    }
}
