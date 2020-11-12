import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-display-board',
  templateUrl: './display-board.component.html',
  styleUrls: ['./display-board.component.css']
})
export class DisplayBoardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() userCount = 0;
  @Output() getUsersEvent = new EventEmitter();

  getAllUsers() {
    this.getUsersEvent.emit('get all users');
  }

}
