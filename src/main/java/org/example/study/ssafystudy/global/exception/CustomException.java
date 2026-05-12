package org.example.study.ssafystudy.global.exception;

import lombok.Getter;
import org.example.study.ssafystudy.global.exception.error.ErrorCode;

@Getter
public class CustomException  extends RuntimeException {

    private final ErrorCode errorCode;

    // Q. 왜 @RequiredArgsConstructor를 쓰지 않는가?
    // A. 롬복은 현재 클래스 필드를 채워주는 생성자만 만들어주기 때문. 부모 클래스 생성자를 호출하면서 특정 값을 넘겨주지는 못함.

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // 부모에게 메시지를 전달함
        this.errorCode = errorCode; // 내 필드를 초기화함.
    }


}
