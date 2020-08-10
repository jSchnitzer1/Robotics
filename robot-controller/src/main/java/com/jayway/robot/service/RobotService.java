package com.jayway.robot.service;

import com.jayway.robot.exception.ErrorCode;
import com.jayway.robot.exception.RobotNotInBoundException;
import com.jayway.robot.exception.IllegalRoomArgumentException;
import com.jayway.robot.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.jayway.robot.model.Direction.findById;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RobotService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotService.class);
    public static final char START_DIRECTION = 'N';
    public static final int MOVE_STEP = 1;
    Direction currentDirection;

    public String move(String command, String roomStr, int xCurrentPosition, int yCurrentPosition) {
        RoomType roomType = Enum.valueOf(RoomType.class, roomStr);
        currentDirection = findById(START_DIRECTION);
        Room room = switch (roomType) {
            case SQUARE -> new SquareRoom(0, 0, 5, 5);
            case CIRCLE -> new CircularRoom(10);
            default -> throw new IllegalRoomArgumentException("Incorrect room type", ErrorCode.INCORRECT_ROOM_TYPE);
        };
        Point currentRobotPosition = room.getStartPosition(xCurrentPosition, yCurrentPosition);
        List<Character> commands = command.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        commands.forEach(c -> {
            switch (Command.findCommand(c)) {
                case LEFT -> currentDirection = findById(currentDirection.getLeftDirectionId());
                case RIGHT -> currentDirection = findById(currentDirection.getRightDirectionId());
                case FORWARD -> {
                    if(currentDirection == null) throw new IllegalRoomArgumentException("Direction cannot be null", ErrorCode.INVALID_DIRECTION);
                    switch (currentDirection.getDirectionId()){
                        case 'N' -> currentRobotPosition.y = (int) currentRobotPosition.getY() + MOVE_STEP;
                        case 'Ö' -> currentRobotPosition.x = (int) currentRobotPosition.getX() + MOVE_STEP;
                        case 'S' -> currentRobotPosition.y = (int) currentRobotPosition.getY() - MOVE_STEP;
                        case 'V' -> currentRobotPosition.x = (int) currentRobotPosition.getX() - MOVE_STEP;
                        default -> throw new IllegalRoomArgumentException("Invalid direction " + currentDirection.getDirectionId(), ErrorCode.INVALID_COMMAND);
                    }
                }
                default -> throw new IllegalRoomArgumentException("Invalid command " + c.toString(), ErrorCode.INVALID_COMMAND);
            }
        });


        if (!room.contains(currentRobotPosition)) {
            LOGGER.error("Robot not in the room. Robot type: %s. Location: x: %d; y: %d", roomStr, currentRobotPosition.x, currentRobotPosition.y);
            throw new RobotNotInBoundException("Robot not in the room", ErrorCode.ROBOT_NOT_INBOUND);
        }

        return new StringBuilder()
                .append(currentRobotPosition.x)
                .append(currentRobotPosition.y)
                .append(currentDirection.getDirectionId())
                .toString();
    }
}
