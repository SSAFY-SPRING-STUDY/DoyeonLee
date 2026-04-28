package org.example.springstudy.domain.auth.controller.dto;

public record LoginRequest(
        String loginId,
        String password
) {

}
