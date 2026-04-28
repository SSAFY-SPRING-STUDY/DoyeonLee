package org.example.springstudy.domain.auth.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // 매개변수가 없는 생성자를 만들어주고, private로 만들어주겠다.
public class AuthorizationUtils {

    // 토큰을 파싱해줘야 함.
    public static String getAccessToken(String authHeader) {
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        throw new IllegalArgumentException("토큰에 문제가 있습니다.");
    }
}
