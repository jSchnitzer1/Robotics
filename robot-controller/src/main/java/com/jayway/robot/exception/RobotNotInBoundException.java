package com.jayway.robot.exception;

public class RobotNotInBoundException extends RuntimeException {
    public static final long serialVersionID = 1L;
    private final ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public RobotNotInBoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
