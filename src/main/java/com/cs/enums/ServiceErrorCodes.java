package com.cs.enums;

import org.springframework.http.HttpStatus;

public enum ServiceErrorCodes {

    INVALID_REQUEST_PARAMETERS("1001", HttpStatus.BAD_REQUEST, "Invalid parameters"),
    REGISTRATION_INFO_NULL("1002", HttpStatus.BAD_REQUEST, "The registration info is null"),
    USERNAME_OR_PASSWORD_NOT_PROVIDE("1003", HttpStatus.FORBIDDEN, "username or password not provide"),
    USERNAME_NOT_FOUND("1004", HttpStatus.FORBIDDEN, "username not found");

    private final String errorCode;
    private final HttpStatus httpStatusCode;
    private final String errorInfo;

    private ServiceErrorCodes(String errorCode, HttpStatus httpStatusCode, String errorInfo) {
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return "ERR" + errorCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
