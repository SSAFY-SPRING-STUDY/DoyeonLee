package org.example.study.ssafystudy.global.exception;

import lombok.Getter;
import org.example.study.ssafystudy.global.exception.error.ErrorCode;

@Getter
public class CustomException  extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // 부모에게 메시지를 전달함
        this.errorCode = errorCode; // 내 필드를 초기화함.
    }


}
