package org.example.springstudy.domain.auth.component;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID; // 랜덤값 만들기 위해서
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public String createSession(Long id){
        String token = UUID.randomUUID().toString();
        sessionStore.put(token,id);
        return token;
    }

    public void removeSession(String accessToken) {
        sessionStore.remove(accessToken);
    }

    public Optional<Long> getMemberId(String accessToken) {
        return Optional.ofNullable(sessionStore.get(accessToken));
    }
}
