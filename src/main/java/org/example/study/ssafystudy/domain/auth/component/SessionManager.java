package org.example.study.ssafystudy.domain.auth.component;

import org.example.study.ssafystudy.global.exception.CustomException;
import org.example.study.ssafystudy.global.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public String createSession(Long memberId){
        String sessionKey = UUID.randomUUID().toString();
        sessionStore.put(sessionKey, memberId);
        return sessionKey;
    }

    public void removeSession(String sessionKey){
        sessionStore.remove(sessionKey);
    }

    public long getMemberId(String sessionKey){
        Long memberId = sessionStore.get(sessionKey);

        if(memberId == null){
            // 여기도 CustomException 사용!
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        return memberId;
    }


}
