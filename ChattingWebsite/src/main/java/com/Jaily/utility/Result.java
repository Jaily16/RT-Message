package com.Jaily.utility;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {

    private T data;
    private Integer code;
    private String msg;

    public Result(Integer code, T data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, T data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
