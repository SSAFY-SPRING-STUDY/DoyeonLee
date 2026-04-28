package org.example.springstudy.domain.auth.controller.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {

}
