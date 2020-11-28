import { Component, OnInit } from '@angular/core';  
import Swal from 'sweetalert2/dist/sweetalert2.js';  

@Component({
  selector: 'app-alertmsg-component',
  templateUrl: './alertmsg-component.component.html',
  styleUrls: ['./alertmsg-component.component.css']
})
export class AlertmsgComponentComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

 

  alertSuccess(){  
    Swal.fire(' ')  ;
  }  

}
