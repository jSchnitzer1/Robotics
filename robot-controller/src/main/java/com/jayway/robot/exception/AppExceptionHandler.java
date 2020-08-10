package com.jayway.robot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalRoomArgumentException.class})
    public ResponseEntity<?> handleCommandException(IllegalRoomArgumentException exception, WebRequest webRequest) {
        String errorMessageDesc = exception.getLocalizedMessage() != null ? exception.getLocalizedMessage() : exception.toString();
        return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getLocalizedMessage(), exception.getErrorCode()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RobotNotInBoundException.class})
    public ResponseEntity<?> handleRobotNotInBoundExceptionException(RobotNotInBoundException exception, WebRequest webRequest) {
        String errorMessageDesc = exception.getLocalizedMessage() != null ? exception.getLocalizedMessage() : exception.toString();
        return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getLocalizedMessage(), exception.getErrorCode()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest webRequest) {
        String errorMessageDesc = exception.getLocalizedMessage() != null ? exception.getLocalizedMessage() : exception.toString();
        return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getLocalizedMessage(), ErrorCode.RUNTIME_ERROR),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
