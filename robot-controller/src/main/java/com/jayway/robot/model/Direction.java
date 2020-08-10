package com.jayway.robot.model;

import com.jayway.robot.exception.ErrorCode;
import com.jayway.robot.exception.IllegalRoomArgumentException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Direction {
    private static final Logger LOGGER = LoggerFactory.getLogger(Direction.class);
    public static final List<Direction> DIRECTIONS = List.of(
            new Direction('N', "North", 'V', 'Ö'),
            new Direction('Ö', "East", 'N', 'S'),
            new Direction('S', "South", 'Ö', 'V'),
            new Direction('V', "West", 'S', 'N')
    );

    private char directionId;
    private String directionName;
    private char leftDirectionId;
    private char rightDirectionId;

    public Direction(char directionId, String directionName, char leftDirectionId, char rightDirectionId) {
        this.directionId = directionId;
        this.directionName = directionName;
        this.leftDirectionId = leftDirectionId;
        this.rightDirectionId = rightDirectionId;
    }

    public static Direction findById(char directionId) {
        Optional<Direction> directionOp = DIRECTIONS.stream().filter(d -> d.getDirectionId() == directionId).findFirst();
        if(!directionOp.isPresent()) {
            LOGGER.error("Invalid direction %s", directionId);
            throw new IllegalRoomArgumentException("Direction [" + directionId + "] is invalid", ErrorCode.INVALID_DIRECTION);
        }
        return directionOp.get();
    }
}
