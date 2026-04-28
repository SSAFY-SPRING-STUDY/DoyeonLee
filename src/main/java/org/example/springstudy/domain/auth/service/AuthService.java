package org.example.springstudy.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.auth.component.SessionManager;
import org.example.springstudy.domain.auth.controller.dto.LoginRequest;
import org.example.springstudy.domain.auth.controller.dto.LoginResponse;
import org.example.springstudy.domain.member.entity.MemberEntity;
import org.example.springstudy.domain.member.repository.MemberRespository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SessionManager sessionManager;
    // 로그인하기 위한 "Member Repository" 작성
    private final MemberRespository memberRespository;

    public LoginResponse login(LoginRequest request) {
        MemberEntity member = memberRespository.findByLoginId(request.loginId())
            .orElseThrow(() -> new RuntimeException("아이디가 올바르지 않습니다."));

        if(member.isValidPassword(request.password())){
            // 똑같으면 토큰 발급
            String token = sessionManager.createSession(member.getId());
            return new LoginResponse(token, "Bearer ");
        }
        // 비밀번호가 다르다면
        throw new RuntimeException("비밀번호가 올바르지 않습니다.");
    }

    public void logout(String accessToken) {
        sessionManager.removeSession(accessToken);
    }

    public Long getMemberId(String accessToken) {
        return sessionManager.getMemberId(accessToken).orElseThrow(
                () -> new RuntimeException("id값 조회 불가")
        );
    }
}
