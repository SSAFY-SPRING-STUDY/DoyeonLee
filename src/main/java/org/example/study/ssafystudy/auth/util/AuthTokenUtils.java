package org.example.study.ssafystudy.auth.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthTokenUtils {


    // 유효하지 않은 경우 false 반환
    public  static boolean isValidBearerToken(String token){
        if (token == null || token.isBlank()) return false;
        return token.startsWith("Bearer "); // Bearer 토큰으로 시작하는지 화깅ㄴ
    }


    // 접두사 제거, 세션 반환
    public static String parseBearerToken(String bearerToken){
        // "Bearer " 이후의 값만 반환
        return bearerToken.substring(7);
    }

}
