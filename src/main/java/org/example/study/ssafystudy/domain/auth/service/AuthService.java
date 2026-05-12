package org.example.study.ssafystudy.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.domain.auth.component.SessionManager;
import org.example.study.ssafystudy.domain.auth.controller.dto.LoginRequest;
import org.example.study.ssafystudy.domain.auth.controller.dto.LoginResponse;
import org.example.study.ssafystudy.domain.member.entity.MemberEntity;
import org.example.study.ssafystudy.domain.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    public LoginResponse login(LoginRequest request){

        // username이 없으면 에러
        MemberEntity member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "등록되지 않은 사용자입니다."));

        // 비밀번호가 일치하지 않으면 에러
        if(!member.checkPassword(request.password())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자의 비밀번호를 확인해주세요.");
        }

        // 위 두가지에 안 걸렸으면 세션 생성
        String accessToken = sessionManager.createSession(member.getId());

        return LoginResponse.from(accessToken);
    }

    // 로그아웃 - 세션 삭제
    public void logout(String sessionKey){
        sessionManager.removeSession(sessionKey);
    }

}
