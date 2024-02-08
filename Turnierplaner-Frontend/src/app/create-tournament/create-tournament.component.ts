import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Observable} from 'rxjs';
import { Router } from '@angular/router';
import { MatStepper } from '@angular/material/stepper';

import { Tournament } from '../Objects/tournament';
import { RestService } from '../Services/rest-service';

@Component({
  selector: 'app-create-tournament',
  templateUrl: './create-tournament.component.html',
  styleUrls: ['./create-tournament.component.css']
})
export class CreateTournamentComponent implements OnInit {
  firstFormGroup: FormGroup;
  private rest;

  constructor(private http: HttpClient, private router: Router, private _formBuilder: FormBuilder) {
    this.rest = new RestService(this.http);
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
  }

  redirectFunction(url: string) : void {
    this.router.navigate([url]);
  }

  createTournament(form: NgForm, stepper: MatStepper) {
    var main = this;
    if(form.valid){
      var tournament = new Tournament(form.value.tournamentName, form.value.tournamentDate);
      main.rest.post(tournament, "tournaments").subscribe(
        data => {
          tournament.setTournamentID(data.tournamentId);
        },
        err => console.error('Observer got an error: ' + err),
        () => {
          console.log('Observer got a complete notification');
          this.goForward(stepper);
        }
      );
    }
  }

  goForward(stepper: MatStepper){
    stepper.next();
  }
}
