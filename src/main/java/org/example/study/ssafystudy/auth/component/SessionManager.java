package org.example.study.ssafystudy.auth.component;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "401 Error - 유효하지 않은 세션입니다.");
        }

        return memberId;
    }


}
