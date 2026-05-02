package org.example.study.ssafystudy.auth.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthTokenUtils {


    // 유효하지 않은 경우 true 반환
    public  static boolean isValidBearerToken(String token){
        if (token == null) return false;


        return token.trim().startsWith("Bearer ");
    }


    // 접두사 제거, 세션 반환
    public static String parseBearerToken(String bearerToken){
        // "Bearer " 이후의 값만 반환
        return bearerToken.substring(7);
    }

}
