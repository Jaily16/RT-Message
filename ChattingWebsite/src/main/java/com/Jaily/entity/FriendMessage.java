package com.Jaily.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FriendMessage {
    private String sender;
    private String recipient;
    private Integer type;
    private Date time;
    private String content;
}
