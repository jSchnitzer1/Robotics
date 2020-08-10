package com.jayway.robot.model;

import com.jayway.robot.exception.ErrorCode;
import com.jayway.robot.exception.IllegalRoomArgumentException;

public enum Command {
    LEFT,
    RIGHT,
    FORWARD;

    public static Command findCommand(char command) {
        return switch (command) {
            case 'L', 'V' -> LEFT;
            case 'R', 'H' -> RIGHT;
            case 'F', 'G' -> FORWARD;
            default -> throw new IllegalRoomArgumentException("Invalid command -> " + command, ErrorCode.INVALID_COMMAND);
        };
    }
}
