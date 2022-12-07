package com.Jaily.utility;

import lombok.ToString;

@ToString
public enum Code {
    //LOGIN CODE
    LOGIN_SUCCESS(10),
    LOGIN_USERNAME_NOT_FOUND(11),
    LOGIN_PASSWORD_INCORRECT(12);

    private Integer code;

    Code(Integer code) {
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }
}
