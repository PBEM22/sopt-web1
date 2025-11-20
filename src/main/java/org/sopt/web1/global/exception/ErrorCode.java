package org.sopt.web1.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50001, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final int code;
    private final String msg;
}
