package com.jayway.robot.model;

import java.awt.*;

public interface Room {
    java.awt.Point getStartPosition();
    boolean contains(java.awt.Point position);
    default public java.awt.Point getStartPosition(int x, int y) {
        return new Point(x, y);
    }
}
