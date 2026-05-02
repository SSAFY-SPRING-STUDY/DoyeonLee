package org.example.study.ssafystudy.auth.controller.dto;

public record LoginResponse(
        String accessToken,
        String TokenType
) {

    public static LoginResponse from(String accessToken){
        return new LoginResponse(accessToken, "Bearer ");
    }
}
