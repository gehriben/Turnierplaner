import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable} from 'rxjs';
import { Router } from '@angular/router';

import { Participant } from '../Objects/participant';
import { RestService } from '../Services/rest-service';


@Component({
  selector: 'app-add-participant',
  templateUrl: './add-participant.component.html',
  styleUrls: ['./add-participant.component.css']
})


export class AddParticipantComponent implements OnInit {
  private _participantList = [];
  private rest;

  constructor(private http: HttpClient, private router: Router) {
     this.rest = new RestService(this.http);
  }

  ngOnInit() {
  }

  redirectFunction(url : string) : void {
    this.router.navigate([url]);
  }

  fillParticipantList(form: NgForm) {
    if(form.valid){
      var participant = new Participant(form.value.firstName, form.value.lastName, form.value.dateOfBirth, form.value.residence);
      this._participantList.push(participant);
    }
  }

  submitParticipants(){
    var main = this;
    this._participantList.forEach(function(participant){
      main.rest.post(participant, "participant").subscribe(
        data => {
          participant.setParticipantId(data.participantId);
        },
        err => console.error('Observer got an error: ' + err),
        () => console.log('Observer got a complete notification')
      );
    });
    this.redirectFunction("/");
  }
}
