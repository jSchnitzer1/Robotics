import { TestBed } from '@angular/core/testing';

import { MoveRobotService } from './move-robot.service';

describe('MoveRobotService', () => {
  let service: MoveRobotService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MoveRobotService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
