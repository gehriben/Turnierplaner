import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {RestService} from "../Services/rest-service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  public tournaments$;

  private restService;

  constructor(private http: HttpClient, private router : Router) {
    this.restService = new RestService(this.http);
  }

  ngOnInit() {
    this.tournaments$ = this.restService.getAll("tournaments");
  }

  redirectFunction(url : string,id : number) : void {
    this.router.navigate([url],{ queryParams: { id: id } });
  }
}
