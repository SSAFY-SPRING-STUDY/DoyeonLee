package org.example.study.ssafystudy.domain.auth.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthTokenUtils {

    private static final String AUTH_TOKEN_PREFIX = "Bearer ";
    // 유효하지 않은 경우 false 반환
    public  static boolean isValidBearerToken(String token){
        if (token == null || token.isBlank()) return false;
        return token.startsWith(AUTH_TOKEN_PREFIX);
    }

    // 접두사 제거, 세션 반환
    public static String parseBearerToken(String bearerToken){
        return bearerToken.substring(AUTH_TOKEN_PREFIX.length()).trim();
    }

}
