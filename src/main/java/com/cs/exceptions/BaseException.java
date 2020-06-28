package com.cs.exceptions;

import com.cs.enums.ServiceErrorCodes;

public class BaseException extends RuntimeException {
    private ServiceErrorCodes errorCode;

    public BaseException(ServiceErrorCodes errorCode) {
        super(errorCode.getErrorInfo());
    }

    public BaseException(Throwable cause, ServiceErrorCodes errorCode) {
        super(errorCode.getErrorInfo(), cause);
    }

    public ServiceErrorCodes getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getErrorInfo();
    }
}
