package org.example.study.ssafystudy.global.response;


import org.example.study.ssafystudy.global.exception.error.ErrorCode;

public record ApiResponse<T>(
        String message,
        T data
) {

    // 1. 성공적으로 응답 - 데이터 있음.
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<T>(message, data);
    }

    // 2. 성공 응답 - 데이터 없음.
    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<Void>(message, null);
    }

    // 3. 에러 응답 - 데이터 있음.
    public static <T> ApiResponse<T> error(ErrorCode errorCode, T data) {
        return new ApiResponse<>(errorCode.getMessage(), data);
    }

    // 4. 에러 응답 - 데이터 없음.
    public static ApiResponse<Void> error(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getMessage(), null);
    }

}
