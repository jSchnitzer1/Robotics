package com.jayway.robot.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    private Date timestamp;
    private String message;
    private ErrorCode errorCode;

    public ErrorMessage() {
    }

    public ErrorMessage(Date timestamp, String message, ErrorCode errorCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorCode = errorCode;
    }
}
