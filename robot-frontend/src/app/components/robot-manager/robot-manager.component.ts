import {AfterContentInit, AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormGroup, Validators, FormControl} from "@angular/forms";
import {MoveRobotService} from "../../services/move-robot.service";

@Component({
  selector: 'app-robot-manager',
  templateUrl: './robot-manager.component.html',
  styleUrls: ['./robot-manager.component.css']
})
export class RobotManagerComponent implements OnInit, AfterViewInit {
  @ViewChild('robotContainer') robotContainer: ElementRef;
  @ViewChild('robotImg') robotImg: ElementRef;
  @ViewChild('robotDirection') robotDirection: ElementRef;
  public roomType: string;
  public command: string;
  public xPosition: number;
  public yPosition: number;
  public newPosition: string;
  public direction: string = "N";
  moveForm = new FormGroup({
    agreement: new FormControl(false)
  })

  constructor(private moveRobotService: MoveRobotService) {
    this.roomType = 'SQUARE'
    this.command = "HGHGGHGHG";
    this.xPosition = 1;
    this.yPosition = 2;

  }

  ngAfterViewInit(): void {
    this.positionRobotInScreen(this.xPosition, this.yPosition, 'N');
  }

  ngOnInit(): void {

  }

  onSubmit() {
    console.log("command: " + this.command);
    console.log("roomType: " + this.roomType);
    console.log("xPosition: " + this.xPosition);
    console.log("yPosition: " + this.yPosition);

    this.moveRobotService.moveRobot(this.command, this.roomType, this.xPosition, this.yPosition).subscribe(
      position => {
        this.newPosition = position.replace('-', '');
        let posArr = this.newPosition.split('');
        console.log("posArr", posArr);
        let x: number = parseInt(posArr[0]) > 0 ? parseInt(posArr[0]) : parseInt(posArr[0]) * -1;
        let y: number = parseInt(posArr[1]) > 0 ? parseInt(posArr[1]) : parseInt(posArr[1]) * -1;
        this.positionRobotInScreen(x, y, posArr[2]);
      },
      error => {
        let jsonError = JSON.parse(error.error);
        this.positionRobotInScreen(100, 100, "");
        this.newPosition = "Error: " + jsonError.message
      }
    );
  }

  private positionRobotInScreen(x: number, y: number, direction: string) {


    let offsetWidth: number = this.robotContainer.nativeElement.offsetWidth;
    let offsetHeight: number = this.robotContainer.nativeElement.offsetHeight;

    let xRobotCurrentPos: number = (offsetWidth * x) / 5;
    let yRobotCurrentPos: number = (offsetHeight * y) / 5;

    console.log(xRobotCurrentPos, yRobotCurrentPos)

    this.robotImg.nativeElement.style.left = xRobotCurrentPos + "px";
    this.robotImg.nativeElement.style.top = yRobotCurrentPos + "px";

    this.robotDirection.nativeElement.style.left = (xRobotCurrentPos + 10) + "px";
    this.robotDirection.nativeElement.style.top = (yRobotCurrentPos + 30) + "px";
    this.direction = direction;

  }
}
