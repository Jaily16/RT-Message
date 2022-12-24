package com.Jaily.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    TEXT(0),
    FILE(1);
    private final Integer type;
}
