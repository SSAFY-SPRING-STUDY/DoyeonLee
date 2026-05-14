package org.example.study.ssafystudy.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.domain.auth.component.SessionManager;
import org.example.study.ssafystudy.domain.auth.controller.dto.LoginRequest;
import org.example.study.ssafystudy.domain.auth.controller.dto.LoginResponse;
import org.example.study.ssafystudy.domain.member.entity.MemberEntity;
import org.example.study.ssafystudy.domain.member.repository.MemberRepository;
import org.example.study.ssafystudy.global.exception.CustomException;
import org.example.study.ssafystudy.global.exception.error.ErrorCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    public LoginResponse login(LoginRequest request){

        // username이 없으면 에러
        MemberEntity member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        // 비밀번호가 일치하지 않으면 에러
        if(!member.checkPassword(request.password())){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        String accessToken = sessionManager.createSession(member.getId());
        return LoginResponse.from(accessToken);
    }

    // 로그아웃 - 세션 삭제
    public void logout(String sessionKey){
        sessionManager.removeSession(sessionKey);
    }

}
