package org.example.springstudy.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.auth.service.AuthService;
import org.example.springstudy.domain.auth.util.AuthorizationUtils;
import org.example.springstudy.domain.member.controller.dto.MemberRequest;
import org.example.springstudy.domain.member.controller.dto.MemberResponse;
import org.example.springstudy.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final AuthService authService;


    @PostMapping
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest request){
        MemberResponse response = memberService.save(request); // 여기서 save 메서드를 꼭 static으로 지정해야하는이유?
        // 지정하지 않으면 에러가 발생하는 이유??
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> me(@RequestHeader("Authorization") String authHeader){
        MemberResponse response = null;
        try{
            String accessToken = AuthorizationUtils.getAccessToken(authHeader);
            Long memberId = authService.getMemberId(accessToken);
            response = memberService.findById(memberId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // http 에러 코드를 반환하라
        }
        return ResponseEntity.ok(response);
    }
}
