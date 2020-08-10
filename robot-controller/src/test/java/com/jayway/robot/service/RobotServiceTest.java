package com.jayway.robot.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import com.jayway.robot.exception.ErrorCode;
import com.jayway.robot.exception.IllegalRoomArgumentException;
import com.jayway.robot.exception.RobotNotInBoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class RobotServiceTest {

    @TestConfiguration
    static class RobotServiceImplTestContextConfiguration {
        @Bean
        public RobotService robotService() {
            return new RobotService();
        }
    }

    @Autowired
    RobotService robotService;

    /**
     * Test moving inside a SQUARE room at position (1,2)
     */
    @Test
    public void moveSquare() {
        String position = robotService.move("HGHGGHGHG", "SQUARE", 1, 2);
        assertTrue(position.equals("11N"));
    }

    /**
     * Test moving inside a CIRCLE room at position (0,0)
     */
    @Test
    public void moveCircle() {
        String position = robotService.move("RRFLFFLRF", "CIRCLE", 0, 0);
        assertThat(position, is(equalTo("3-1Ö")));
    }

    /**
     * Test moving outside a SQUARE room at position (1,2) - RobotNotInBoundException
     */
    @Test
    public void moveSquare_RobotNotInBoundException() {
        RobotNotInBoundException exception = assertThrows(RobotNotInBoundException.class, () -> robotService.move("HGHGGVGGHGHG", "SQUARE", 1, 2));
        assertTrue(exception.getMessage().equals("Robot not in the room"));
        assertThat(exception.getErrorCode(), is(ErrorCode.ROBOT_NOT_INBOUND));
    }

    /**
     * Test moving outside a CIRCLE room at position (1,2) - RobotNotInBoundException
     */
    @Test
    public void moveCirlce_RobotNotInBoundException() {
        RobotNotInBoundException exception = assertThrows(RobotNotInBoundException.class, () -> robotService.move("RRFLFFGGFFLRFGGF", "CIRCLE", 0, 0));
        assertTrue(exception.getMessage().equals("Robot not in the room"));
        assertThat(exception.getErrorCode(), is(ErrorCode.ROBOT_NOT_INBOUND));
    }

    /**
     * Test invalid command - IllegalRoomArgumentException
     */
    @Test
    public void moveCirlce_IllegalRoomArgumentException() {
        IllegalRoomArgumentException exception = assertThrows(IllegalRoomArgumentException.class, () -> robotService.move("ACGH", "CIRCLE", 0, 0));
        assertTrue(exception.getMessage().contains("Invalid command"));
        assertThat(exception.getErrorCode(), is(ErrorCode.INVALID_COMMAND));
    }
}
