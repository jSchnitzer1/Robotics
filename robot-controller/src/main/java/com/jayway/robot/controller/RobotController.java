package com.jayway.robot.controller;

import com.jayway.robot.service.RobotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/robot")
@Validated
public class RobotController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotController.class);

    @Autowired
    private RobotService robotService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/move", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity move(@RequestParam("command") @Valid @NotBlank @Size(max = 50) String command,
                               @RequestParam("room") @Valid @NotBlank @Size(max = 10) String room,
                               @RequestParam("xCurrentPosition") @Valid @Min(value = 0) int xCurrentPosition,
                               @RequestParam("yCurrentPosition") @Valid @Min(value = 0) int yCurrentPosition) {
        LOGGER.info("Moving robot...");
        return new ResponseEntity<>(robotService.move(command.toUpperCase(), room.toUpperCase(), xCurrentPosition, yCurrentPosition), HttpStatus.OK);
    }
}
