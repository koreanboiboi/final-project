import { Component, OnInit, Output } from '@angular/core';
import SpotifyWebApi from 'spotify-web-api-js'
import { SpotifyAuthService } from './spotify-auth.service';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  ngOnInit(): void {

    // this.spotifyLogin();
    // this.spotSvc.requestAccessToken();
    this.disabled = false;
  }

  constructor(private spotSvc: SpotifyAuthService){}

  spotifyLogin(){
    this.spotSvc.spotifyLogin();
  }

  disabled = false;

  enabled = true;

  isDisabled(){
    this.disabled = true;
    this.enabled = false;
  }

  logout(){
    this.enabled = true;
    this.disabled = false;
  }


}
