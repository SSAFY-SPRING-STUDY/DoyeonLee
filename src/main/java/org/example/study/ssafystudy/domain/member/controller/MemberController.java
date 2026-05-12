package org.example.study.ssafystudy.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.domain.auth.component.SessionManager;
import org.example.study.ssafystudy.domain.auth.util.AuthTokenUtils;
import org.example.study.ssafystudy.domain.member.controller.dto.MemberRequest;
import org.example.study.ssafystudy.domain.member.controller.dto.MemberResponse;
import org.example.study.ssafystudy.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;


    // 회원가입 - status coderk 201 created
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse join(@RequestBody MemberRequest request){
        return memberService.join(request);
    }

    // get 방식으로 내 정보 가져오기
    @GetMapping("/me")

    public MemberResponse getMyInfo(@RequestHeader("Authorization") String bearerToken){

        boolean isValid = AuthTokenUtils.isValidBearerToken(bearerToken);

        // 토큰 유효성 검사 -> false - 유효하지 않은 토큰이라면
        if(!isValid){ // 유효하지 않은 토큰이라면
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");
        }

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        Long memberId = sessionManager.getMemberId(sessionKey);

        return memberService.getMemberInfo(memberId);

    }


}
