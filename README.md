# Robotics
Jayway Robotics Manager

## Requirements
(Open) JDK 14

Node.js v14.7.0

npm v6.14.7 

Maven 3 (https://maven.apache.org/)

An IDE (VS Code or IntelliJ)

## System composition 

### robot-controller (Backend)
#### Controller Package
contains:
- RobotController: has the API to move the robot with all required http validations

#### Exception Package
contains:
- IllegalRoomArgumentException: Exception handler that catches the invalid robot commands 
- RobotNotInBoundException: Exception handler that handles robot when goes outside of the room
- AppExceptionHandler: Application level exception handler, which handles also bad requests and internal server errors 
- ErrorCode: Enumerator that produces error code and types
- ErrorMessage: A class for error message used in previous exception handlers 

#### Model Package
contains: 
- Room interface: handles room interface methods 
- CircularRoom: implementation class for a Circular Room
- SquareRoom: implementation class for a Square Room 
- RoomType: Enumerator for the room type
- Direction: a class handles robot directions, comparisons, static direction values
- Command: Enumeration for commands 

#### Service Package
contains
- RobotService: a business logic to move a robot including all required validations

### Unit Testing
#### Controller Testing
- Test moving a SQUARE room at position (1,2)
- Test moving inside a CIRCLE room at position (0,0)
- Test RUNTIME Error (invalid position)
- Test RUNTIME Error (invalid room)

#### RobotServiceTest
- Test moving inside a SQUARE room at position (1,2)
- Test moving inside a CIRCLE room at position (0,0)
- Test moving outside a SQUARE room at position (1,2) - RobotNotInBoundException
- Test moving outside a CIRCLE room at position (1,2) - RobotNotInBoundException
- Test invalid command - IllegalRoomArgumentException

### robot-frontend (Frontend)
Developed using Angular 10

#### MoveRobotService 
A service calls backend to move the robot based on the give location using post method

#### RobotManagerComponent (using Angular Material)

A package of typescript code and Angular code 

- A form design (using Angular Material UI) to move the robot
- Dynamically show the current position of the robot on the page that simulates the room
- Typescript code to move the robot (call the MoveRobotService) and validates and handles errors.

## Run the application
### Backend Robot API
```
$ sh run-backend.sh
```
### Frontend Angular Application
```
$ sh run-frontend.sh
```

Notes:
- Each shell command should be executed in a different terminal tab (one for running the web service and the second one for running the Angular App)
- The frontend should go live at http://localhost:4200/

## Nice-to-have for later (not implemented due to limited time)
- API access protection via api keys
- Dockerization
- Professional UI with UX engineer (currently designed by a backend engineer)
- More tests

## Screenshots



