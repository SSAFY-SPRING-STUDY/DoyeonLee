package org.example.study.ssafystudy.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.auth.component.SessionManager;
import org.example.study.ssafystudy.auth.util.AuthTokenUtils;
import org.example.study.ssafystudy.member.controller.dto.MemberRequest;
import org.example.study.ssafystudy.member.controller.dto.MemberResponse;
import org.example.study.ssafystudy.member.service.MemberService;
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
    public MemberResponse getMyInfo(@RequestParam("bearerToken") String bearerToken){

        boolean isValid = AuthTokenUtils.isValidBearerToken(bearerToken);


        // 토큰 유효성 검사
        if(!AuthTokenUtils.isValidBearerToken(bearerToken)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");
        }

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);

        Long memberId = sessionManager.getMemberId(sessionKey);

        return memberService.getMemberInfo(memberId);

    }


}
