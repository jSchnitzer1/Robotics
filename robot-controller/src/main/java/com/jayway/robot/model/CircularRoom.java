package com.jayway.robot.model;

import com.jayway.robot.exception.ErrorCode;
import com.jayway.robot.exception.IllegalRoomArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class CircularRoom implements Room {
    private static final Logger LOGGER = LoggerFactory.getLogger(CircularRoom.class);
    private final Integer RADIUS;

    public CircularRoom(int radius) {
        this.RADIUS = radius;
    }

    @Override
    public Point getStartPosition() {
        return new Point(0, 0);
    }

    @Override
    public boolean contains(Point position) {
        if (position == null) {
            LOGGER.error("Position point must have a value");
            throw new IllegalRoomArgumentException("Position point must have a value", ErrorCode.NULL_POINT);
        }
        return (position.distance(getStartPosition()) < RADIUS);
    }
}
