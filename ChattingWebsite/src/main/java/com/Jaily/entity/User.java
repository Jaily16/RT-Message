package com.Jaily.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String lastname;
    private String firstname;
    private String password;
    private String birthday;
    private Boolean sex;
    private String avatar;
}
