package com.cs.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * UserRegistrationException
 *
 * @date 2019/08/12
 */
public class UserInfoNotPrrovidException extends AuthenticationException {

    private static final long serialVersionUID = 235306605816039324L;
    private Integer errorCode;

    public UserInfoNotPrrovidException(String errorMsg) {
        super(errorMsg);
        this.errorCode = 500;
    }

    public UserInfoNotPrrovidException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

}
