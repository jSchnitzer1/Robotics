package com.jayway.robot.model;

import com.jayway.robot.exception.ErrorCode;
import com.jayway.robot.exception.IllegalRoomArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class SquareRoom implements Room {
    private static final Logger LOGGER = LoggerFactory.getLogger(SquareRoom.class);
    private final Rectangle SQUARE_ROOM;

    public SquareRoom(int x, int y, int width, int height) {
        SQUARE_ROOM = new Rectangle(x, y, width, height);
    }

    @Override
    public Point getStartPosition() {
        return new Point(1, 2);
    }

    @Override
    public boolean contains(Point position) {
        if (position == null) {
            LOGGER.error("Position point must have a value");
            throw new IllegalRoomArgumentException("Position point must have a value", ErrorCode.NULL_POINT);
        }
        return SQUARE_ROOM.contains(position);
    }
}
