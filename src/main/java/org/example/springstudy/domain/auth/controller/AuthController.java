package org.example.springstudy.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.auth.controller.dto.LoginRequest;
import org.example.springstudy.domain.auth.controller.dto.LoginResponse;
import org.example.springstudy.domain.auth.service.AuthService;
import org.example.springstudy.domain.auth.util.AuthorizationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // login api 작성
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = null;
        try {
            response = authService.login(request);
        }
        catch(RuntimeException e){

            // 기억 - HttpStatus는 http 상태 코드를 자바 코드로 읽기 쉽게 표현한 것!
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }

    // Logout api 작성
    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(@RequestHeader("Authorization") String authHeader){ // 받은 입토큰 값을 받아오겠다.
        // authHeader = "token"
        String accessToken = AuthorizationUtils.getAccessToken(authHeader);
        authService.logout(accessToken);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }
}
