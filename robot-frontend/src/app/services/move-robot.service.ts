import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MoveRobotService {
  formatUrl = (command, roomType, xPosition, yPosition) => `http://localhost:8085/api/robot/move/?command=${command}&room=${roomType}&xCurrentPosition=${xPosition}&yCurrentPosition=${yPosition}`;

  headerOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
      'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
    }),
    responseType: 'text' as 'json'
  };

  constructor(private http: HttpClient) { }

  moveRobot(command:string, roomType:string, xPosition:number, yPosition:number): Observable<string> {
    var headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return this.http
      .post<string>(this.formatUrl(command, roomType, xPosition, yPosition), "",  this.headerOptions);
  }
}
