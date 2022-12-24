package com.Jaily.entity;

import lombok.Data;

import java.util.Date;

@Data
public class GroupMessage {
    private String groupId;
    private String sender;
    private Integer type;
    private Date time;
    private String content;
}
