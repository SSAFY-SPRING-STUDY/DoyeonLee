package org.example.study.ssafystudy.domain.auth.controller;


import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.domain.auth.controller.dto.LoginRequest;
import org.example.study.ssafystudy.domain.auth.controller.dto.LoginResponse;
import org.example.study.ssafystudy.domain.auth.service.AuthService;
import org.example.study.ssafystudy.domain.auth.util.AuthTokenUtils;
import org.example.study.ssafystudy.global.exception.CustomException;
import org.example.study.ssafystudy.global.exception.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        // 세션 키만 추출
        String sessionKey = AuthTokenUtils.parseBearerToken(authHeader);

        // 서비스에게 로그아웃 요청 - 세션 삭제
        authService.logout(sessionKey);
    }


}
