import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {RestService} from "../Services/rest-service";

@Component({
  selector: 'app-pre-start-tournament',
  templateUrl: './pre-start-tournament.component.html',
  styleUrls: ['./pre-start-tournament.component.css']
})
export class PreStartTournamentComponent implements OnInit {

  public participants;
  public tournament = "";
  private param;
  private restService;

    constructor(private http: HttpClient, private router : Router, private ar :ActivatedRoute) {
      this.restService = new RestService(this.http);
      
      ar.queryParams.subscribe(params => {
        this.param = params['id'];       
    });
    
  }
  

  ngOnInit() {
    this.participants = this.restService.getAll("participants");
    this.restService.get(this.param, "tournaments/").subscribe(
      data => {this.tournament = data;},
      err => {console.log(err);}

    );
    
  }


  redirectFunction(url : string) : void {
    this.router.navigate([url]);
  }

}
