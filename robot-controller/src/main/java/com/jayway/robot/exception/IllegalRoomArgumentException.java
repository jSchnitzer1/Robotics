package com.jayway.robot.exception;

public class IllegalRoomArgumentException extends IllegalArgumentException {
    public static final long serialVersionID = 1L;
    private final ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public IllegalRoomArgumentException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
