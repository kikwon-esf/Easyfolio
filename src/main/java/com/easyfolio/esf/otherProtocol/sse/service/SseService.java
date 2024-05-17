package com.easyfolio.esf.otherProtocol.sse.service;

import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.otherProtocol.sse.repository.SseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SseService {
    private final SseRepository sseRepository;
    public SseEmitter loginSSE(String id, List<AlarmVO> list){

        SseEmitter emitter = createEmitter(id);

        sendAlarmList(id,list);
        return emitter;
    }
    public void notify(String id, List<AlarmVO> list){
        sendAlarmList(id,list);
    }

    private void sendAlarmList(String id, List<AlarmVO> list){
        SseEmitter emitter = sseRepository.get(id);
        if(emitter != null){
            try{
                emitter.send(SseEmitter.event().id(id).name("notification").data(list));
            }catch (IOException e){
                sseRepository.deleteById(id);
                emitter.completeWithError(e);
            }
        }
    }
    private SseEmitter createEmitter(String id){
        //SseEmitter객체 생성 -> 기본생성자 초기 타임아웃 30분
        SseEmitter emitter = new SseEmitter();
        sseRepository.save(id, emitter);

        // Emitter가 완료될 때(모든 데이터가 성공적으로 전송된 상태) Emitter를 삭제한다.
//        emitter.onCompletion(() -> sseRepository.deleteById(id));
        // Emitter가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때) Emitter를 삭제한다.
//        emitter.onTimeout(() -> sseRepository.deleteById(id));

        return emitter;
    }


}
