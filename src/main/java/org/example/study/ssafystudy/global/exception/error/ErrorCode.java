package org.example.study.ssafystudy.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 에러 코드 9개
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST,"400", "잘못된 요청 파라미터입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401","로그인이 필요한 서비스입니다."),
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "403","해당 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500","서버 내부 오류가 발생했습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "404","존재하지 않는 회원입니다."),
    INVALID_USERNAME(HttpStatus.UNAUTHORIZED,"401","아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "401","아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "401","토큰 정보가 유효하지 않습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "404","존재하지 않는 게시글입니다.")
    ;

    // 필드
    private final HttpStatus status;
    private final String code; // 추가 - http 상태 코드 표시
    private final String message;


}
