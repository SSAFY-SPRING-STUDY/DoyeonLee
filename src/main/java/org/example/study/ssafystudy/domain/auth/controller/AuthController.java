package org.example.study.ssafystudy.domain.auth.controller;


import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.domain.auth.controller.dto.LoginRequest;
import org.example.study.ssafystudy.domain.auth.controller.dto.LoginResponse;
import org.example.study.ssafystudy.domain.auth.service.AuthService;
import org.example.study.ssafystudy.domain.auth.util.AuthTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader(value = "Authorization") String authHeader){

        if(!AuthTokenUtils.isValidBearerToken(authHeader)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "토큰이 없거나 형식이 잘못되었습니다.");
        }

        // 세션 키만 추출
        String sessionKey = AuthTokenUtils.parseBearerToken(authHeader);

        // 서비스에게 로그아웃 요청 - 세션 삭제
        authService.logout(sessionKey);
    }


}
