package org.example.study.ssafystudy.global.exception;


import lombok.extern.slf4j.Slf4j;
import org.example.study.ssafystudy.global.exception.error.ErrorCode;
import org.example.study.ssafystudy.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외 처리용
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("CustomException:{}", errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode));
    }

    // 커스템 예외가 아닌 예외 처리 .
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(Exception e) {
        log.error("Internal Server Error: ",e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>("서버 내부 오류가 발생했습니다.",null));
    }




}
