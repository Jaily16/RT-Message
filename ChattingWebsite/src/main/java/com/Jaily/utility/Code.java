package com.Jaily.utility;

import lombok.ToString;

@ToString
public enum Code {
    //LOGIN CODE
    LOGIN_SUCCESS(10),
    LOGIN_USERNAME_NOT_FOUND(11),
    LOGIN_PASSWORD_INCORRECT(12),
    VERIFY_SUCCESS(13),
    VERIFY_FAILED(14),
    LOGOUT(15),

    //REGISTER CODE
    REGISTER_SUCCESS(20),
    REGISTER_FAILED(21),
    REGISTER_NAME_HAS_BEEN_USED(22),
    REGISTER_NAME_PASS(23),

    //CHANGED INFO CODE
    USER_INFO_CHANGED_SUCCESS(24),
    USER_INFO_CHANGED_FAILED(25),

    //SEARCH CODE
    SEARCH_USER_SUCCESS(30),
    SEARCH_USER_FAILED(31),

    //FRIEND CODE
    FRIEND_ADD_SUCCESS(40),
    FRIEND_ADD_AGAIN(41),
    FRIEND_LIST_LOAD(42),
    FRIEND_DELETE_SUCCESS(43);


    private Integer code;

    Code(Integer code) {
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }
}
